import axios from "axios";
import { User } from "./types/user";

export default async function registerUser(user: User, token?: string) {
  const apiUrl = process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";
  await axios.post(`${apiUrl}/funcionario`, user, {
    headers: {
      Authorization: `Bearer ${token}`
    }
  });
}
