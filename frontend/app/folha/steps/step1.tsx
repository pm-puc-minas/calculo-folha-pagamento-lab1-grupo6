"use client";

import { useFormContext } from "react-hook-form";
import SelectInput from "@/components/select-input";
import Input from "@/components/input";

interface Step1Props {
  funcionarios: any[];
  selectedFuncionario: any;
  isAdmin: boolean;
  meses: { id: string; value: string }[];
}

export default function Step1({ funcionarios, selectedFuncionario, isAdmin, meses }: Step1Props) {
  const { register, formState: { errors } } = useFormContext();

  return (
    <div className="flex flex-col gap-6">
      <h2 className="text-2xl font-bold text-gray-800">Dados Iniciais</h2>
      
      {isAdmin && (
        <SelectInput
          id="funcionarioId"
          label="Nome do Colaborador"
          placeholder="Selecione..."
          options={funcionarios.map(f => ({ id: f.id, value: f.nome || f.email }))}
          register={register("funcionarioId", { required: "Selecione um funcionário" })}
          error={errors.funcionarioId?.message as string}
        />
      )}

      <div className="grid grid-cols-2 gap-4">
        <div className="flex flex-col gap-1">
          <label className="text-sm font-medium text-gray-700">Cargo</label>
          <input 
            disabled 
            value={selectedFuncionario?.cargo || "-"} 
            className="p-3 bg-gray-100 border rounded-lg text-gray-600"
          />
        </div>
        <div className="flex flex-col gap-1">
          <label className="text-sm font-medium text-gray-700">Salário Bruto (R$)</label>
          <input 
            disabled 
            value={selectedFuncionario?.salarioBruto || "-"} 
            className="p-3 bg-gray-100 border rounded-lg text-gray-600"
          />
        </div>
      </div>

      <div className="grid grid-cols-2 gap-4">
        <SelectInput
          id="month"
          label="Mês de Referência"
          placeholder="Selecione"
          options={meses}
          register={register("month", { required: "Obrigatório" })}
          error={errors.month?.message as string}
        />
        <Input
          id="year"
          label="Ano"
          type="number"
          placeholder="2024"
          register={register("year", { required: "Obrigatório" })}
          error={errors.year?.message as string}
        />
      </div>

      <div className="grid grid-cols-2 gap-4">
        <Input
          id="diasTrabalhados"
          label="Dias Trabalhados"
          type="number"
          placeholder="22"
          register={register("diasTrabalhados", { required: "Obrigatório" })}
          error={errors.diasTrabalhados?.message as string}
        />
        <Input
          id="jornadaSemanal"
          label="Carga Horária Semanal"
          type="number"
          placeholder="44"
          register={register("jornadaSemanal", { required: "Obrigatório" })}
          error={errors.jornadaSemanal?.message as string}
        />
      </div>
    </div>
  );
}
