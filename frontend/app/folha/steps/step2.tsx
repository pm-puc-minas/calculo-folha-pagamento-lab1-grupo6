"use client";

import { useFormContext } from "react-hook-form";

export default function Step2() {
  const { register } = useFormContext();

  return (
    <div className="flex flex-col gap-6">
      <h2 className="text-2xl font-bold text-gray-800">Adicionais</h2>
      
      <div className="bg-gray-50 p-4 rounded-lg border">
        <label className="block text-sm font-medium text-gray-700 mb-2">O funcionário recebe adicional de periculosidade?</label>
        <div className="flex gap-4">
          <label className="flex items-center gap-2 cursor-pointer">
            <input type="radio" value="SIM" {...register("periculosidade")} className="w-4 h-4 text-blue-600" />
            <span>Sim</span>
          </label>
          <label className="flex items-center gap-2 cursor-pointer">
            <input type="radio" value="NAO" {...register("periculosidade")} className="w-4 h-4 text-blue-600" />
            <span>Não</span>
          </label>
        </div>
      </div>

      <div className="bg-gray-50 p-4 rounded-lg border">
        <label className="block text-sm font-medium text-gray-700 mb-2">O funcionário recebe adicional de insalubridade?</label>
        <div className="flex flex-col gap-2">
          <label className="flex items-center gap-2 cursor-pointer">
            <input type="radio" value="NAO" {...register("insalubridade")} className="w-4 h-4 text-blue-600" />
            <span>Não</span>
          </label>
          <label className="flex items-center gap-2 cursor-pointer">
            <input type="radio" value="BAIXO" {...register("insalubridade")} className="w-4 h-4 text-blue-600" />
            <span>Sim, grau Baixo (10%)</span>
          </label>
          <label className="flex items-center gap-2 cursor-pointer">
            <input type="radio" value="MEDIO" {...register("insalubridade")} className="w-4 h-4 text-blue-600" />
            <span>Sim, grau Médio (20%)</span>
          </label>
          <label className="flex items-center gap-2 cursor-pointer">
            <input type="radio" value="ALTO" {...register("insalubridade")} className="w-4 h-4 text-blue-600" />
            <span>Sim, grau Alto (40%)</span>
          </label>
        </div>
      </div>
    </div>
  );
}
