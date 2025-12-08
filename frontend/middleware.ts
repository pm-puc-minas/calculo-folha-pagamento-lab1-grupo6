import { withAuth } from "next-auth/middleware";
import { NextResponse } from "next/server";

export default withAuth(
  function middleware(req) {
    const token = req.nextauth.token;
    const isAuth = !!token;
    const isLoginPage = req.nextUrl.pathname.startsWith("/login");

    if (isLoginPage) {
      if (isAuth) {
        if (token?.role === "ADMIN") {
          return NextResponse.redirect(new URL("/folha", req.url));
        }
        return NextResponse.redirect(new URL("/dashboard", req.url));
      }
      return null;
    }

    if (req.nextUrl.pathname.startsWith("/folha") || req.nextUrl.pathname.startsWith("/cadastro")) {
      if (token?.role !== "ADMIN") {
        return NextResponse.redirect(new URL("/dashboard", req.url));
      }
    }

    return NextResponse.next();
  },
  {
    callbacks: {
      authorized: ({ token }) => !!token,
    },
    pages: {
      signIn: "/login",
    },
  }
);

export const config = {
  matcher: ["/folha/:path*", "/cadastro/:path*", "/dashboard/:path*"],
};
