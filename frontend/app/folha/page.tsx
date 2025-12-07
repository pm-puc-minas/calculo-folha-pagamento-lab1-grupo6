"use client";

import { useState } from "react";
import { useForm, FieldValues } from "react-hook-form";
import { useSession } from "next-auth/react";
import Input from "@/components/input";
import SelectInput from "@/components/select-input";
import { Button } from "@/components/button";
import { Header } from "@/components/header";
import { toast } from "react-toastify";

export default function FolhaPage() {
  const { data: session } = useSession();

  const [step, setStep] = useState(1);
  const [resultado, setResultado] = useState<any>(null);
  const [loading, setLoading] = useState(false);

  /** --------------------
   * FORMATAÇÃO DE NÚMEROS
   ----------------------*/
  function formatNumberInput(value: string): string {
    value = value.replace(/[^\d.,]/g, "");
    value = value.replace(",", ".");
    const parts = value.split(".");
    if (parts.length > 2) {
      value = parts.shift() + "." + parts.join("");
    }
    if (value.startsWith(".")) value = "0" + value;
    return value;
  }

  /** --------------------
   * REACT-HOOK-FORM SETUP
   ----------------------*/
  const {
    register,
    handleSubmit,
    trigger,
    watch,
    formState: { errors },
  } = useForm({
    defaultValues: {
      periculosidade: "",
      insalubridade: "",
      valorValeAlimentacaoDiario: "",
      valeTransporteRecebido: "",
      numeroDeDependentes: "",
      valorPensaoAlimenticia: "",
      diasTrabalhados: "",
      cargaDiaria: "",
      jornadaMensal: "",
      jornadaSemanal: "",
      horasExtra: "",
      year: "",
      month: "",
      monthValue: "",
      leapYear: false,
    },
  });

  /** --------------------
   * ETAPAS DO FORMULÁRIO
   ----------------------*/

  const goNext = async () => {
    let fieldsToValidate: string[] = [];

    if (step === 1) fieldsToValidate = ["periculosidade", "insalubridade"];
    if (step === 2)
      fieldsToValidate = [
        "valorValeAlimentacaoDiario",
        "valeTransporteRecebido",
        "numeroDeDependentes",
        "valorPensaoAlimenticia",
      ];
    if (step === 3)
      fieldsToValidate = [
        "year",
        "month",
        "monthValue",
        "diasTrabalhados",
        "cargaDiaria",
        "jornadaMensal",
        "jornadaSemanal",
        "horasExtra",
      ];

    const valid = await trigger(fieldsToValidate);
    if (!valid) {
      toast.error("Preencha todos os campos obrigatórios antes de prosseguir.");
      return;
    }

    setStep((s) => Math.min(4, s + 1));
  };

  const goPrev = () => setStep((s) => Math.max(1, s - 1));

  const meses = [
    { id: "JANUARY", value: "Janeiro" },
    { id: "FEBRUARY", value: "Fevereiro" },
    { id: "MARCH", value: "Março" },
    { id: "APRIL", value: "Abril" },
    { id: "MAY", value: "Maio" },
    { id: "JUNE", value: "Junho" },
    { id: "JULY", value: "Julho" },
    { id: "AUGUST", value: "Agosto" },
    { id: "SEPTEMBER", value: "Setembro" },
    { id: "OCTOBER", value: "Outubro" },
    { id: "NOVEMBER", value: "Novembro" },
    { id: "DECEMBER", value: "Dezembro" },
  ];

  /** --------------------
   * ENVIO FINAL
   ----------------------*/

  const onFinalSubmit = async (data: FieldValues) => {
    setLoading(true);
    setResultado(null);

    const parse = (v: any) => Number(String(v || "0").replace(",", "."));

    try {
      const payload = {
        mes: {
          year: parse(data.year),
          month: data.month,
          monthValue: parse(data.monthValue),
          leapYear: Boolean(data.leapYear),
        },
        diasTrabalhados: parse(data.diasTrabalhados),
        jornadaMensal: parse(data.jornadaMensal),
        jornadaSemanal: parse(data.jornadaSemanal),
        valeTransporteRecebido: parse(data.valeTransporteRecebido),
        cargaDiaria: parse(data.cargaDiaria),
        horasExtra: parse(data.horasExtra),
        valorValeAlimentacaoDiario: parse(data.valorValeAlimentacaoDiario),
        numeroDeDependentes: parse(data.numeroDeDependentes),
        valorPensaoAlimenticia: parse(data.valorPensaoAlimenticia),
      };

      const apiUrl = process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8080";

      const res = await fetch(`${apiUrl}/folha/gerar-folha`, {
        method: "POST",
        headers: {
          "Content-Type": "application/json",
          ...(session?.idToken
            ? { Authorization: `Bearer ${session.idToken}` }
            : {}),
        },
        body: JSON.stringify(payload),
      });

      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || `Erro ${res.status}`);
      }

      const json = await res.json();
      setResultado(json);
      toast.success("Folha gerada com sucesso!");
    } catch (error: any) {
      toast.error(error?.message || "Erro ao gerar folha");
    } finally {
      setLoading(false);
    }
  };

  const values = watch();

  /** --------------------
   * COMPONENTES DE ETAPA
   ----------------------*/

  function StepIndicator() {
    return (
      <div className="flex items-center justify-center gap-2 mb-8">
        {[1, 2, 3, 4].map((n, idx) => (
          <div key={n} className="flex items-center gap-2">
            <div
              className={`w-3 h-3 rounded-full ${
                step >= n ? "bg-blue-500" : "bg-gray-300"
              }`}
            />
            {idx < 3 && (
              <div
                className={`w-10 h-0.5 ${
                  step > n ? "bg-blue-500" : "bg-gray-300"
                }`}
              />
            )}
          </div>
        ))}
      </div>
    );
  }

  /** -------------------- ETAPA 1 -------------------- */

  function Step1() {
    return (
      <>
        <h2 className="text-xl font-bold mb-6 text-center">
          Adicionais de Periculosidade e Insalubridade
        </h2>

        <SelectInput
          id="periculosidade"
          label="Periculosidade"
          placeholder="Selecione"
          options={[
            { id: "SIM", value: "Sim" },
            { id: "NAO", value: "Não" },
          ]}
          register={register("periculosidade", { required: true })}
          error={errors.periculosidade?.message as string}
        />

        <div className="mt-4">
          <SelectInput
            id="insalubridade"
            label="Insalubridade"
            placeholder="Selecione"
            options={[
              { id: "NAO", value: "Não" },
              { id: "BAIXO", value: "Baixo (10%)" },
              { id: "MEDIO", value: "Médio (20%)" },
              { id: "ALTO", value: "Alto (40%)" },
            ]}
            register={register("insalubridade", { required: true })}
            error={errors.insalubridade?.message as string}
          />
        </div>

        <div className="flex justify-end mt-8">
          <Button label="Próximo" onClick={goNext} />
        </div>
      </>
    );
  }

  /** -------------------- ETAPA 2 -------------------- */

  function Step2() {
    return (
      <>
        <h2 className="text-xl font-bold mb-6 text-center">
          Benefícios e Deduções
        </h2>

        <Input
          id="valorValeAlimentacaoDiario"
          type="text"
          label="Valor Diário do Vale Alimentação"
          placeholder="Ex: 20.00"
          register={register("valorValeAlimentacaoDiario", {
            required: "Obrigatório",
            onChange: (e) =>
              (e.target.value = formatNumberInput(e.target.value)),
          })}
          error={errors.valorValeAlimentacaoDiario?.message}
        />

        <Input
          id="valeTransporteRecebido"
          type="text"
          label="Valor Total do Vale Transporte"
          placeholder="Ex: 150.00"
          register={register("valeTransporteRecebido", {
            required: "Obrigatório",
            onChange: (e) =>
              (e.target.value = formatNumberInput(e.target.value)),
          })}
          error={errors.valeTransporteRecebido?.message}
        />

        <Input
          id="numeroDeDependentes"
          type="text"
          label="Número de Dependentes"
          placeholder="Ex: 2"
          register={register("numeroDeDependentes", {
            required: "Obrigatório",
            onChange: (e) =>
              (e.target.value = formatNumberInput(e.target.value)),
          })}
          error={errors.numeroDeDependentes?.message}
        />

        <Input
          id="valorPensaoAlimenticia"
          type="text"
          label="Valor da Pensão Alimentícia"
          placeholder="Ex: 0.00"
          register={register("valorPensaoAlimenticia", {
            required: "Obrigatório",
            onChange: (e) =>
              (e.target.value = formatNumberInput(e.target.value)),
          })}
          error={errors.valorPensaoAlimenticia?.message}
        />

        <div className="flex justify-between mt-8">
          <Button label="Voltar" onClick={goPrev} />
          <Button label="Próximo" onClick={goNext} />
        </div>
      </>
    );
  }

  /** -------------------- ETAPA 3 -------------------- */

  function Step3() {
    return (
      <>
        <h2 className="text-xl font-bold mb-6 text-center">
          Informações Gerais
        </h2>

        <div className="grid grid-cols-2 gap-4">
          <Input
            id="year"
            type="text"
            label="Ano"
            placeholder="Ex: 2024"
            register={register("year", {
              required: "Obrigatório",
              onChange: (e) =>
                (e.target.value = formatNumberInput(e.target.value)),
            })}
            error={errors.year?.message}
          />

          <SelectInput
            id="month"
            label="Mês"
            placeholder="Selecione"
            options={meses}
            register={register("month", { required: "Obrigatório" })}
            error={errors.month?.message as string}
          />

          <Input
            id="monthValue"
            type="text"
            label="Month Value"
            placeholder="Ex: 10"
            register={register("monthValue", {
              required: "Obrigatório",
              onChange: (e) =>
                (e.target.value = formatNumberInput(e.target.value)),
            })}
            error={errors.monthValue?.message}
          />

          <div className="flex items-center gap-2 mt-6">
            <input type="checkbox" {...register("leapYear")} id="leapYear" className="w-4 h-4" />
            <label htmlFor="leapYear" className="text-sm text-gray-700">
              Ano Bissexto?
            </label>
          </div>

          <Input
            id="diasTrabalhados"
            type="text"
            label="Dias Trabalhados"
            placeholder="Ex: 22"
            register={register("diasTrabalhados", {
              required: "Obrigatório",
              onChange: (e) =>
                (e.target.value = formatNumberInput(e.target.value)),
            })}
            error={errors.diasTrabalhados?.message}
          />

          <Input
            id="cargaDiaria"
            type="text"
            label="Carga Diária (horas)"
            placeholder="Ex: 8"
            register={register("cargaDiaria", {
              required: "Obrigatório",
              onChange: (e) =>
                (e.target.value = formatNumberInput(e.target.value)),
            })}
            error={errors.cargaDiaria?.message}
          />

          <Input
            id="jornadaMensal"
            type="text"
            label="Jornada Mensal (horas)"
            placeholder="Ex: 176"
            register={register("jornadaMensal", {
              required: "Obrigatório",
              onChange: (e) =>
                (e.target.value = formatNumberInput(e.target.value)),
            })}
            error={errors.jornadaMensal?.message}
          />

          <Input
            id="jornadaSemanal"
            type="text"
            label="Jornada Semanal (horas)"
            placeholder="Ex: 40"
            register={register("jornadaSemanal", {
              required: "Obrigatório",
              onChange: (e) =>
                (e.target.value = formatNumberInput(e.target.value)),
            })}
            error={errors.jornadaSemanal?.message}
          />

          <Input
            id="horasExtra"
            type="text"
            label="Horas Extras"
            placeholder="Ex: 5"
            register={register("horasExtra", {
              required: "Obrigatório",
              onChange: (e) =>
                (e.target.value = formatNumberInput(e.target.value)),
            })}
            error={errors.horasExtra?.message}
          />
        </div>

        <div className="flex justify-between mt-8">
          <Button label="Voltar" onClick={goPrev} />
          <Button label="Revisar" onClick={goNext} />
        </div>
      </>
    );
  }

  /** -------------------- ETAPA 4 -------------------- */

  function Step4() {
    return (
      <>
        <h2 className="text-xl font-bold mb-6 text-center">Revisão Final</h2>

        <div className="bg-gray-100 p-4 rounded border">
          <h3 className="font-semibold mb-2">Adicionais</h3>
          <p>Periculosidade: {values.periculosidade}</p>
          <p>Insalubridade: {values.insalubridade}</p>

          <h3 className="font-semibold mt-4 mb-2">Benefícios e Deduções</h3>
          <p>VA Diário: {values.valorValeAlimentacaoDiario}</p>
          <p>VT Total: {values.valeTransporteRecebido}</p>
          <p>Dependentes: {values.numeroDeDependentes}</p>
          <p>Pensão Alimentícia: {values.valorPensaoAlimenticia}</p>

          <h3 className="font-semibold mt-4 mb-2">Informações Gerais</h3>
          <p>Ano: {values.year}</p>
          <p>Mês: {values.month}</p>
          <p>MonthValue: {values.monthValue}</p>
          <p>Ano Bissexto: {values.leapYear ? "Sim" : "Não"}</p>
          <p>Dias Trabalhados: {values.diasTrabalhados}</p>
          <p>Carga Diária: {values.cargaDiaria}</p>
          <p>Jornada Mensal: {values.jornadaMensal}</p>
          <p>Jornada Semanal: {values.jornadaSemanal}</p>
          <p>Horas Extras: {values.horasExtra}</p>
        </div>

        <div className="flex justify-between mt-8">
          <Button label="Voltar" onClick={goPrev} />
          <Button
            label={loading ? "Enviando..." : "Gerar Folha"}
            onClick={handleSubmit(onFinalSubmit)}
          />
        </div>

        {resultado && (
          <div className="mt-6 p-4 bg-white border rounded">
            <h3 className="font-semibold mb-2">Resultado</h3>
            <pre className="text-sm overflow-auto">
              {JSON.stringify(resultado, null, 2)}
            </pre>
          </div>
        )}
      </>
    );
  }

  /** -------------------- RENDER PRINCIPAL -------------------- */

  return (
    <div>
      <Header />

      <div className="flex justify-center mt-10 px-4">
        <div className="bg-white border rounded p-8 w-full max-w-3xl">
          <StepIndicator />

          {step === 1 && <Step1 />}
          {step === 2 && <Step2 />}
          {step === 3 && <Step3 />}
          {step === 4 && <Step4 />}
        </div>
      </div>
    </div>
  );
}
