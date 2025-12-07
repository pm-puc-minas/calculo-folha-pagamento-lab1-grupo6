"use client";

import { Button } from "@/components/button";
import { Header } from "@/components/header";
import Input from "@/components/input";
import login from "@/domain/login";
import { LoginBody } from "@/domain/types/login";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { FieldValues, useForm } from "react-hook-form";
import { FaRegEye, FaRegEyeSlash } from "react-icons/fa";
import { toast } from "react-toastify";

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
      toast.success("Login realizado com sucesso!");
      router.push("/");
    } catch (error: any) {
      toast.error(error.message || "Erro ao fazer login");
    }
  };

  return (
    <div className="bg-gray-100 h-screen w-screen flex items-center flex-col gap-20">
      <Header />
      <div className="flex flex-col items-center gap-5">
        <h1 className="text-black font-bold">Login</h1>
        <form
          className="border-gray-200 rounded border p-5 gap-10 flex flex-col"
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
