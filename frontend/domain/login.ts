import { signIn } from "next-auth/react";
import { LoginBody } from "./types/login";

export default async function login(loginData: LoginBody) {
  const result = await signIn("credentials", {
    email: loginData.email,
    password: loginData.password,
    redirect: false,
  });

  if (result?.error) {
    return Promise.reject(new Error(result.error));
  }
}