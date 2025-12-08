"use client";

import { Poppins } from "next/font/google";
import "./globals.css";
import { Header } from "@/components/header";
import { ToastContainer } from "react-toastify";
import { SessionProvider } from "next-auth/react";

const poppins = Poppins({
  weight: ["300", "400", "500", "600", "700"],
  subsets: ["latin"],
  variable: "--font-poppins",
});

export default function RootLayout({
  children,
}: {
  children: React.ReactNode;
}) {
  return (
    <html lang="pt-br">
      <body className={`${poppins.variable} font-sans antialiased`}>
        <SessionProvider>
          <Header />
          <ToastContainer position="top-right" autoClose={3000} />
          {children}
        </SessionProvider>
      </body>
    </html>
  );
}
