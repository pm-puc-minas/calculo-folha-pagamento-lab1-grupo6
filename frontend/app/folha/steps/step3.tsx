"use client";

import { useFormContext } from "react-hook-form";
import Input from "@/components/input";

export default function Step3() {
  const { register } = useFormContext();

  return (
    <div className="flex flex-col gap-6">
      <h2 className="text-2xl font-bold text-gray-800">Benefícios e Deduções</h2>
      
      <Input
        id="valeTransporteRecebido"
        label="Valor Total do Vale Transporte (R$)"
        type="text"
        placeholder="0,00"
        register={register("valeTransporteRecebido")}
      />

      <div className="grid grid-cols-2 gap-4">
        <Input
          id="valorValeAlimentacaoDiario"
          label="Valor Diário do VA (R$)"
          type="text"
          placeholder="0,00"
          register={register("valorValeAlimentacaoDiario")}
        />
      </div>

      <div className="grid grid-cols-2 gap-4">
        <Input
          id="numeroDeDependentes"
          label="Número de Dependentes"
          type="number"
          placeholder="0"
          register={register("numeroDeDependentes")}
        />
        <Input
          id="valorPensaoAlimenticia"
          label="Pensão Alimentícia (R$)"
          type="text"
          placeholder="0,00"
          register={register("valorPensaoAlimenticia")}
        />
      </div>
    </div>
  );
}
