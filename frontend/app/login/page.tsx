"use client";

import { Button } from "@/components/button";
import Input from "@/components/input";
import login from "@/domain/login";
import { LoginBody } from "@/domain/types/login";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { FieldValues, useForm } from "react-hook-form";
import { FaRegEye, FaRegEyeSlash } from "react-icons/fa";
import { toast } from "react-toastify";
import { getSession } from "next-auth/react";

export default function Login() {
  const router = useRouter();
  const [showPassword, setShowPassword] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = async (data: FieldValues) => {
    try {
      await login(data as LoginBody);
      const session = await getSession();
      toast.success("Login realizado com sucesso!");
      
      if (session?.user?.role === "ADMIN") {
        router.push("/");
      } else {
        router.push("/dashboard");
      }
    } catch (error: any) {
      toast.error(error.message || "Erro ao fazer login");
    }
  };

  return (
    <div className="bg-gray-50 h-[80vh] flex items-center justify-center">
      <div className="flex flex-col items-center gap-5 w-full max-w-md">
        <h1 className="text-3xl font-bold text-gray-800 mb-4">Login</h1>
        <form
          className="bg-white p-8 rounded-lg shadow-sm border border-gray-100 w-full flex flex-col gap-6"
          onSubmit={handleSubmit(onSubmit)}
        >
          <Input
            label="Email"
            type="email"
            id="email"
            placeholder="seu@email.com"
            register={register("email", {
              required: "O email é obrigatório",
              pattern: {
                value: /^[^@ ]+@[^@ ]+\.[^@ .]{2,}$/,
                message: "Digite um email válido",
              },
            })}
            error={errors.email?.message as string}
          />
          <div className="flex items-end">
            <Input
              label="Senha"
              id="password"
              type={showPassword ? "text" : "password"}
              placeholder="******"
              register={register("password", {
                required: "A senha é obrigatória",
              })}
              error={errors.password?.message as string}
            />
            {showPassword ? (
              <FaRegEyeSlash
                className="mb-3 cursor-pointer text-gray-400"
                onClick={() => setShowPassword(false)}
              />
            ) : (
              <FaRegEye
                className="mb-3 cursor-pointer text-gray-400"
                onClick={() => setShowPassword(true)}
              />
            )}
          </div>
        <Button label="Entrar" type="submit" />
        </form>
      </div>
    </div>
  );
}
