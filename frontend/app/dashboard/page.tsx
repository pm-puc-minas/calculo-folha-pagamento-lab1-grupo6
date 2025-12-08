"use client";
import { useSession } from "next-auth/react";
import { useEffect, useState } from "react";

export default function Dashboard() {
  const { data: session } = useSession();
  const [folhas, setFolhas] = useState<any[]>([]);

  useEffect(() => {
    if (session?.idToken) {
      fetch(`${process.env.NEXT_PUBLIC_API_URL}/folha/folhas`, {
        headers: { Authorization: `Bearer ${session.idToken}` }
      })
      .then(res => {
        if (!res.ok) {
          if (res.status === 403) {
            throw new Error("Acesso negado. Verifique suas permissões.");
          }
          if (res.status === 401) {
            throw new Error("Sessão inválida ou expirada. Por favor, faça logout e login novamente.");
          }
          throw new Error(`Erro na requisição: ${res.status}`);
        }
        return res.json();
      })
      .then(data => setFolhas(data))
      .catch(err => {
        console.error(err);
        if (err.message.includes("Sessão inválida")) {
           alert(err.message);
        }
      });
    }
  }, [session]);

  return (
    <div className="bg-gray-50 h-[80vh] pt-10">
      <div className="p-10 max-w-4xl mx-auto">
        <h1 className="text-2xl font-bold mb-5">Minhas Folhas de Pagamento</h1>
        <div className="grid gap-4">
          {folhas.length === 0 ? (
            <p>Nenhuma folha encontrada.</p>
          ) : (
            folhas.map((folha) => (
              <div key={folha.id} className="border p-6 rounded bg-white shadow">
                <div className="grid grid-cols-2 gap-4">
                  <div>
                    <p className="text-gray-600">Mês/Ano</p>
                    <p className="font-bold">{folha.mes}</p>
                  </div>
                  <div>
                    <p className="text-gray-600">Salário Líquido</p>
                    <p className="font-bold text-green-600">R$ {folha.salarioLiquido?.toFixed(2)}</p>
                  </div>
                  <div>
                    <p className="text-gray-600">Salário Bruto</p>
                    <p className="font-bold">R$ {folha.funcionario?.salarioBruto?.toFixed(2)}</p>
                  </div>
                  <div>
                    <p className="text-gray-600">Descontos</p>
                    <p className="text-sm">INSS: R$ {folha.inss?.toFixed(2)}</p>
                    <p className="text-sm">IRRF: R$ {folha.irrf?.toFixed(2)}</p>
                  </div>
                </div>
              </div>
            ))
          )}
        </div>
      </div>
    </div>
  );
}
