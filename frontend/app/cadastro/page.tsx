"use client";

import { Button } from "@/components/button";
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
import { useSession } from "next-auth/react";

export default function Cadastro() {
  const { data: session } = useSession();
  const router = useRouter();
  const [showPassword, setShowPassword] = useState(false);
  const {
    register,
    handleSubmit,
    formState: { errors },
  } = useForm();

  const onSubmit = async (data: FieldValues) => {
    try {
      await registerUser(data as User, session?.idToken);
      toast.success("Cadastro realizado com sucesso!");
      router.push("/");
    } catch (error: any) {
      const msg = error.response?.data?.message || error.message || "Erro ao realizar cadastro";
      toast.error(msg);
    }
  };

  return (
    <div className="bg-gray-50 h-[80vh] w-full flex items-center justify-center py-10">
      <div className="flex flex-col items-center gap-5 w-full max-w-2xl">
        <h1 className="text-3xl font-bold text-gray-800">Cadastro</h1>
        <form
          className="bg-white p-8 rounded-lg shadow-sm border border-gray-100 w-full flex flex-col gap-6"
          onSubmit={handleSubmit(onSubmit)}
        >
          <Input
            label="Nome"
            type="text"
            id="nome"
            placeholder="Nome completo"
            register={register("nome", {
              required: "O nome é obrigatório",
            })}
            error={errors.nome?.message as string}
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
              min: { value: 0, message: "O salário não pode ser negativo" }
            })}
            error={errors.salarioBruto?.message as string}
          />
          <Input
            label="CPF"
            type="text"
            id="cpf"
            placeholder="000.000.000-00"
            register={register("cpf", {
              required: "O CPF é obrigatório",
              pattern: {
                value: /^\d{3}\.\d{3}\.\d{3}-\d{2}$|^\d{11}$/,
                message: "CPF inválido (use 11 dígitos ou formato 000.000.000-00)"
              }
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
