import axios from "axios";
import { User } from "./types/user";

export default async function registerUser(user: User) {
  await axios.post("/api/user", user);
}
