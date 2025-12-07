"use client";

import { Button } from "@/components/button";
import { Header } from "@/components/header";
import Input from "@/components/input";
import SelectInput from "@/components/select-input";
import login from "@/domain/login";
import registerUser from "@/domain/register-user";
import { LoginBody } from "@/domain/types/login";
import { User } from "@/domain/types/user";
import { useRouter } from "next/navigation";
import { useState } from "react";
import { FieldValues, useForm } from "react-hook-form";
import { FaRegEye, FaRegEyeSlash } from "react-icons/fa";
import { toast } from "react-toastify";

export default function Cadastro() {
  const router = useRouter();
  const [showPassword, setShowPassword] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = async (data: FieldValues) => {
    try {
      await registerUser(data as User);
      toast.success("Cadastro realizado com sucesso!");
      router.push("/");
    } catch (error: any) {
      toast.error(error.message || "Erro ao realizar cadastro");
    }
  };

  return (
    <div className="bg-gray-100 min-h-screen max-w-screen flex items-center flex-col gap-20 pb-5">
      <Header />
      <div className="flex flex-col items-center gap-5 w-1/2">
        <h1 className="text-black font-bold">Cadastrar funcionário</h1>
        <form
          className="border-gray-200 rounded border p-5 gap-10 flex flex-col w-full"
          onSubmit={handleSubmit(onSubmit)}
        >
          <Input
            label="Nome"
            type="text"
            id="name"
            placeholder="Nome completo"
            register={register("name", {
              required: "O nome é obrigatório",
            })}
            error={errors.name?.message as string}
          />
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
          <SelectInput
            id="cargo"
            label="Cargo"
            placeholder="Selecione o cargo"
            options={[
              { id: "AUXILIAR", value: "Auxiliar" },
              { id: "ESTAGIARIO", value: "Estagiário" },
              { id: "GERENTE", value: "Gerente" },
              { id: "ANALISTA", value: "Analista" },
              { id: "AJUDANTE", value: "Ajudante" },
            ]}
            register={register("cargo", {
              required: "O cargo é obrigatório",
            })}
            error={errors.cargo?.message as string}
          />
          <Input
            label="Salário Bruto"
            type="number"
            id="salarioBruto"
            placeholder="Salário bruto do funcionário"
            register={register("salarioBruto", {
              required: "O salário bruto é obrigatório",
            })}
            error={errors.salarioBruto?.message as string}
          />
          <Input
            label="CPF"
            type="text"
            id="cpf"
            placeholder="CPF do funcionário"
            register={register("cpf", {
              required: "O CPF é obrigatório",
            })}
            error={errors.cpf?.message as string}
          />
          <SelectInput
            id="periculosidade"
            label="Periculosidade"
            placeholder="Selecione a periculosidade"
            options={[
              { id: "SIM", value: "Sim" },
              { id: "NAO", value: "Não" },
            ]}
            register={register("periculosidade", {
              required: "A periculosidade é obrigatória",
            })}
            error={errors.periculosidade?.message as string}
          />
          <SelectInput
            id="insalubridade"
            label="Insalubridade"
            placeholder="Selecione a insalubridade"
            options={[
              { id: "NENHUM", value: "Nenhum" },
              { id: "BAIXO", value: "Baixo" },
              { id: "MEDIO", value: "Médio" },
              { id: "ALTO", value: "Alto" },
            ]}
            register={register("insalubridade", {
              required: "A insalubridade é obrigatória",
            })}
            error={errors.insalubridade?.message as string}
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
        <Button label="Cadastrar" type="submit" />
        </form>
      </div>
    </div>
  );
}
