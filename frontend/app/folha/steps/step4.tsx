"use client";

import { Button } from "@/components/button";

interface Step4Props {
  resultado: any;
  selectedFuncionario: any;
  onReset: () => void;
}

export default function Step4({ resultado, selectedFuncionario, onReset }: Step4Props) {
  if (!resultado) return null;

  return (
    <div className="flex flex-col gap-6">
      <div className="bg-green-50 border border-green-200 rounded-lg p-6 text-center">
        <h2 className="text-2xl font-bold text-green-800 mb-2">Cálculo Realizado com Sucesso!</h2>
        <p className="text-green-700">Confira abaixo o demonstrativo de pagamento.</p>
      </div>

      <div className="bg-white border border-gray-200 rounded-lg shadow-sm overflow-hidden">
        <div className="bg-gray-50 px-6 py-4 border-b border-gray-200">
          <h3 className="font-bold text-gray-700">Dados do Funcionário</h3>
        </div>
        <div className="p-6 grid grid-cols-2 gap-4">
          <div>
            <p className="text-sm text-gray-500">Nome</p>
            <p className="font-medium">{selectedFuncionario?.nome}</p>
          </div>
          <div>
            <p className="text-sm text-gray-500">CPF</p>
            <p className="font-medium">{selectedFuncionario?.cpf}</p>
          </div>
          <div>
            <p className="text-sm text-gray-500">Cargo</p>
            <p className="font-medium">{selectedFuncionario?.cargo}</p>
          </div>
          <div>
            <p className="text-sm text-gray-500">Mês de Referência</p>
            <p className="font-medium">{resultado.mes || "N/A"}</p>
          </div>
        </div>
      </div>

      <div className="bg-white border border-gray-200 rounded-lg shadow-sm overflow-hidden">
        <div className="bg-gray-50 px-6 py-4 border-b border-gray-200 flex justify-between items-center">
          <h3 className="font-bold text-gray-700">Detalhamento</h3>
          <span className="text-sm bg-blue-100 text-blue-800 py-1 px-3 rounded-full font-medium">
            Salário Bruto: R$ {resultado.salarioBruto?.toFixed(2) || selectedFuncionario?.salarioBruto?.toFixed(2)}
          </span>
        </div>
        
        <table className="w-full text-left text-sm">
          <thead className="bg-gray-50 text-gray-500 border-b border-gray-200">
            <tr>
              <th className="px-6 py-3 font-medium">Descrição</th>
              <th className="px-6 py-3 font-medium text-right">Desconto (R$)</th>
            </tr>
          </thead>
          <tbody className="divide-y divide-gray-100">
            <tr>
              <td className="px-6 py-3">INSS</td>
              <td className="px-6 py-3 text-right text-red-600">- {resultado.valorDeDescontoINSS?.toFixed(2)}</td>
            </tr>
            <tr>
              <td className="px-6 py-3">IRRF</td>
              <td className="px-6 py-3 text-right text-red-600">- {resultado.valorDeDescontoIRRF?.toFixed(2)}</td>
            </tr>
            <tr>
              <td className="px-6 py-3">Vale Transporte</td>
              <td className="px-6 py-3 text-right text-red-600">- {resultado.valorDeDescontoVT?.toFixed(2)}</td>
            </tr>
            <tr>
              <td className="px-6 py-3">Vale Alimentação</td>
              <td className="px-6 py-3 text-right text-red-600">- {resultado.valorDeDescontoVA?.toFixed(2)}</td>
            </tr>
            <tr className="bg-gray-50 font-bold text-gray-900">
              <td className="px-6 py-4">Salário Líquido</td>
              <td className="px-6 py-4 text-right text-green-600 text-lg">R$ {resultado.salarioLiquido?.toFixed(2)}</td>
            </tr>
          </tbody>
        </table>
        
        <div className="bg-gray-50 px-6 py-3 border-t border-gray-200 text-xs text-gray-500">
          <span className="font-medium">FGTS (Recolhido pela empresa):</span> R$ {resultado.valorDeDescontoFGTS?.toFixed(2)}
        </div>
      </div>

      <div className="flex justify-end">
        <Button onClick={onReset} label="Novo Cálculo" className="bg-gray-500 text-white hover:bg-gray-600" />
      </div>
    </div>
  );
}
