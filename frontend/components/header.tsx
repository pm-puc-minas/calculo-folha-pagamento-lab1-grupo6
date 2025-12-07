"use client";

import Image from "next/image";
import logo from "@/public/logo.png"
import { Button } from "./button";
import { useRouter } from "next/navigation";


export function Header() {
  const router = useRouter();
  return (
    <header className="flex items-center justify-between p-4 bg-white m-0 w-full">
      <Image src={logo} alt="Logo" width={100} height={50} />
      <div className="flex gap-4">
        <Button label="Login" onClick={() => router.push("/login")} />
        <Button label="Cadastrar" onClick={() => router.push("/cadastro")} />
      </div>
    </header>
  )
}