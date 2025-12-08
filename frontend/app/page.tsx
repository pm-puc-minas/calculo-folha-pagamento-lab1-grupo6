"use client";

import { useSession } from "next-auth/react";
import { useRouter } from "next/navigation";
import { useEffect } from "react";
import { Button } from "@/components/button";
import Image from "next/image";

export default function Home() {
  const { data: session, status } = useSession();
  const router = useRouter();

  useEffect(() => {
    if (status === "unauthenticated") {
      router.push("/login");
    } else if (status === "authenticated" && session?.user?.role === "USER") {
      router.push("/dashboard");
    }
  }, [status, session, router]);

  if (status === "loading") {
    return <div className="flex h-screen items-center justify-center">Carregando...</div>;
  }

  if (session?.user?.role === "ADMIN") {
    return (
      <div className="flex h-[80vh] flex-col items-center justify-center bg-gray-50 gap-8">
        <div className="flex flex-col items-center gap-4">
           <Image 
            src="/logo-all.png" 
            alt="Logo" 
            width={300} 
            height={100} 
            className="mb-4"
          />
          <h1 className="text-2xl font-bold text-gray-800">Bem-vindo, Administrador</h1>
          <p className="text-gray-600">O que você deseja fazer hoje?</p>
        </div>
        
        <div className="flex flex-col gap-4 w-full max-w-md px-4">
          <Button 
            label="Iniciar Novo Cálculo de Folha" 
            onClick={() => router.push("/folha")} 
          />
          <Button 
            label="Cadastrar Novo Funcionário" 
            onClick={() => router.push("/cadastro")} 
            className="bg-white text-blue-600 border border-blue-600 hover:bg-blue-50"
          />
        </div>
      </div>
    );
  }

  return null; // Or a loading spinner while redirecting
}
