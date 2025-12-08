"use client";

import Image from "next/image";
import logo from "@/public/logo.png"
import { Button } from "./button";
import { useRouter } from "next/navigation";
import { signOut, useSession } from "next-auth/react";

export function Header() {
  const router = useRouter();
  const { data: session } = useSession();

  return (
    <header className="flex items-center justify-between px-10 py-4 bg-white w-full shadow-sm">
      <div className="flex items-center gap-2 cursor-pointer" onClick={() => router.push("/")}>
        <h1 className="text-2xl font-bold text-blue-600 flex items-center gap-2">
          <span className="bg-blue-600 text-white p-1 rounded-full text-xs">🍃</span> FolhaCerta
        </h1>
      </div>
      
      <div className="flex items-center gap-6">
        {session ? (
          <>
            <span className="text-gray-600 cursor-pointer hover:text-blue-600" onClick={() => router.push(session.user?.role === "ADMIN" ? "/folha" : "/dashboard")}>
              {session.user?.role === "ADMIN" ? "Novo Cálculo" : "Meu Histórico"}
            </span>
            <div className="flex items-center gap-2">
              <span className="text-gray-800 font-medium">Bem Vindo, {session.user?.name || "Usuário"}</span>
            </div>
            <button onClick={() => signOut({ callbackUrl: "/login" })} className="text-red-500 hover:text-red-700 text-sm font-medium">
              Sair
            </button>
          </>
        ) : (
          <div className="flex gap-4">
            <Button label="Login" onClick={() => router.push("/login")} />
          </div>
        )}
      </div>
    </header>
  )
}