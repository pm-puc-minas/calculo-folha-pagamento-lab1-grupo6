"use client";

import { useState, useEffect } from "react";
import { useForm, FormProvider } from "react-hook-form";
import { useSession } from "next-auth/react";
import { Button } from "@/components/button";
import Step1 from "./steps/step1";
import Step2 from "./steps/step2";
import Step3 from "./steps/step3";
import Step4 from "./steps/step4";
import { toast } from "react-toastify";

export default function FolhaPagamento() {
  const { data: session } = useSession();
  const [step, setStep] = useState(1);
  const [funcionarios, setFuncionarios] = useState<any[]>([]);
  const [selectedFuncionario, setSelectedFuncionario] = useState<any>(null);
  const [resultado, setResultado] = useState<any>(null);
  const [loading, setLoading] = useState(false);

  const methods = useForm({
    defaultValues: {
      funcionarioId: "",
      month: "",
      year: new Date().getFullYear(),
      diasTrabalhados: 22,
      jornadaSemanal: 44,
      periculosidade: "NAO",
      insalubridade: "NAO",
      valeTransporteRecebido: "",
      valorValeAlimentacaoDiario: "",
      numeroDeDependentes: 0,
      valorPensaoAlimenticia: "",
    },
  });

  const { watch, trigger, handleSubmit } = methods;
  const funcionarioId = watch("funcionarioId");

  const meses = [
    { id: "JANUARY", value: "Janeiro" }, { id: "FEBRUARY", value: "Fevereiro" },
    { id: "MARCH", value: "Março" }, { id: "APRIL", value: "Abril" },
    { id: "MAY", value: "Maio" }, { id: "JUNE", value: "Junho" },
    { id: "JULY", value: "Julho" }, { id: "AUGUST", value: "Agosto" },
    { id: "SEPTEMBER", value: "Setembro" }, { id: "OCTOBER", value: "Outubro" },
    { id: "NOVEMBER", value: "Novembro" }, { id: "DECEMBER", value: "Dezembro" }
  ];

  const [historico, setHistorico] = useState<any[]>([]);

  useEffect(() => {
    async function fetchFuncionarios() {
      if (session?.user?.role === "ADMIN" && session?.idToken) {
        try {
           const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/funcionario`, {
             headers: { Authorization: `Bearer ${session.idToken}` }
           });
           const data = await res.json();
           setFuncionarios(data);
        } catch (error) {
          console.error("Erro ao buscar funcionários", error);
        }
      }
    }
    fetchFuncionarios();
  }, [session]);

  useEffect(() => {
    if (funcionarioId) {
      const func = funcionarios.find((f) => f.id.toString() === funcionarioId);
      setSelectedFuncionario(func);
      
      // Fetch history for this employee
      if (session?.idToken) {
        fetch(`${process.env.NEXT_PUBLIC_API_URL}/folha/folhas/${funcionarioId}`, {
          headers: { Authorization: `Bearer ${session.idToken}` }
        })
        .then(res => {
          if (res.ok) return res.json();
          return [];
        })
        .then(data => setHistorico(data))
        .catch(err => console.error("Erro ao buscar histórico", err));
      }
    } else {
      setHistorico([]);
    }
  }, [funcionarioId, funcionarios, session]);

  const handleNext = async () => {
    let isValid = false;

    if (step === 1) {
      isValid = await trigger(["funcionarioId", "month", "year", "diasTrabalhados", "jornadaSemanal"]);
    } else if (step === 2) {
      isValid = await trigger(["periculosidade", "insalubridade"]);
    } else if (step === 3) {
      await handleSubmit(onSubmit)();
      return;
    }

    if (isValid) {
      setStep((prev) => prev + 1);
    }
  };

  const handleBack = () => {
    setStep((prev) => prev - 1);
  };

  const onSubmit = async (data: any) => {
    setLoading(true);
    try {
      const monthMap: { [key: string]: string } = {
        "JANUARY": "01", "FEBRUARY": "02", "MARCH": "03", "APRIL": "04",
        "MAY": "05", "JUNE": "06", "JULY": "07", "AUGUST": "08",
        "SEPTEMBER": "09", "OCTOBER": "10", "NOVEMBER": "11", "DECEMBER": "12"
      };

      const mesFormatado = monthMap[data.month];
      const dataFormatada = `${data.year}-${mesFormatado}`;

      const payload = {
        funcionarioId: data.funcionarioId,
        mes: dataFormatada,
        diasTrabalhados: Number(data.diasTrabalhados),
        jornadaMensal: 220,
        jornadaSemanal: Number(data.jornadaSemanal),
        valeTransporteRecebido: Number(data.valeTransporteRecebido?.toString().replace(",", ".") || 0),
        cargaDiaria: 8,
        horasExtra: 0,
        valorValeAlimentacaoDiario: Number(data.valorValeAlimentacaoDiario?.toString().replace(",", ".") || 0),
        numeroDeDependentes: Number(data.numeroDeDependentes || 0),
        valorPensaoAlimenticia: Number(data.valorPensaoAlimenticia?.toString().replace(",", ".") || 0),
      };

      const apiUrl = process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";
      const res = await fetch(`${apiUrl}/folha/gerar-folha`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          Authorization: `Bearer ${session?.idToken}`
        },
        body: JSON.stringify(payload),
      });

      if (!res.ok) throw new Error(await res.text());
      const json = await res.json();
      setResultado(json);
      setStep(4);
    } catch (error: any) {
      console.error("Erro ao calcular folha", error);
      toast.error(error.message || "Erro ao calcular folha.");
    } finally {
      setLoading(false);
    }
  };

  const handleReset = () => {
    setStep(1);
    setResultado(null);
    setSelectedFuncionario(null);
    methods.reset();
  };

  return (
    <div className="max-w-4xl mx-auto p-6">
      <div className="mb-8 text-center">
        <h1 className="text-3xl font-bold text-gray-800">Cálculo de Folha de Pagamento</h1>
        <p className="text-gray-600 mt-2">Siga os passos para gerar o holerite</p>
      </div>

      {/* Progress Bar */}
      <div className="mb-8">
        <div className="flex justify-between items-center relative">
          <div className="absolute left-0 top-1/2 transform -translate-y-1/2 w-full h-1 bg-gray-200 -z-10"></div>
          {[1, 2, 3, 4].map((s) => (
            <div
              key={s}
              className={`w-10 h-10 rounded-full flex items-center justify-center font-bold transition-colors ${
                step >= s ? "bg-blue-600 text-white" : "bg-gray-200 text-gray-500"
              }`}
            >
              {s}
            </div>
          ))}
        </div>
        <div className="flex justify-between mt-2 text-sm text-gray-600">
          <span>Funcionário</span>
          <span>Período</span>
          <span>Benefícios</span>
          <span>Resultado</span>
        </div>
      </div>

      <div className="bg-white p-8 rounded-xl shadow-lg border border-gray-100">
        <FormProvider {...methods}>
          <form onSubmit={(e) => e.preventDefault()}>
            {step === 1 && (
              <Step1 
                funcionarios={funcionarios} 
                selectedFuncionario={selectedFuncionario}
                isAdmin={session?.user?.role === "ADMIN"}
                meses={meses}
              />
            )}
            {step === 2 && <Step2 />}
            {step === 3 && <Step3 />}
            {step === 4 && (
              <Step4
                resultado={resultado}
                selectedFuncionario={selectedFuncionario}
                onReset={handleReset}
              />
            )}

            {step < 4 && (
              <div className="flex justify-between mt-8 pt-6 border-t border-gray-100">
                <Button
                  label="Voltar"
                  onClick={handleBack}
                  className={`bg-gray-500 hover:bg-gray-600 ${step === 1 ? "invisible" : ""}`}
                />
                <Button 
                  label={loading ? "Calculando..." : step === 3 ? "Calcular Folha" : "Próximo"}
                  onClick={handleNext} 
                  className={loading ? "opacity-50 cursor-not-allowed" : ""}
                />
              </div>
            )}
          </form>
        </FormProvider>
      </div>

      {/* Histórico Section */}
      {selectedFuncionario && historico.length > 0 && step === 1 && (
        <div className="mt-8 bg-white p-8 rounded-xl shadow-lg border border-gray-100">
          <h2 className="text-xl font-bold text-gray-800 mb-4">Histórico de Folhas - {selectedFuncionario.nome}</h2>
          <div className="overflow-x-auto">
            <table className="w-full text-left text-sm">
              <thead className="bg-gray-50 text-gray-500 border-b border-gray-200">
                <tr>
                  <th className="px-4 py-2">Mês/Ano</th>
                  <th className="px-4 py-2">Salário Bruto</th>
                  <th className="px-4 py-2">Descontos</th>
                  <th className="px-4 py-2">Salário Líquido</th>
                </tr>
              </thead>
              <tbody className="divide-y divide-gray-100">
                {historico.map((folha: any) => (
                  <tr key={folha.idFolha}>
                    <td className="px-4 py-2">{folha.mes}</td>
                    <td className="px-4 py-2">R$ {folha.salarioBruto?.toFixed(2) || "-"}</td>
                    <td className="px-4 py-2 text-red-600">
                      R$ {((folha.valorDeDescontoINSS || 0) + (folha.valorDeDescontoIRRF || 0) + (folha.valorDeDescontoVT || 0) + (folha.valorDeDescontoVA || 0)).toFixed(2)}
                    </td>
                    <td className="px-4 py-2 font-bold text-green-600">R$ {folha.salarioLiquido?.toFixed(2)}</td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        </div>
      )}
    </div>
  );
}