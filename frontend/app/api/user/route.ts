import { User } from "@/domain/types/user";
import { api } from "@/lib/axios";
import { getServerSession } from "next-auth";
import { NextResponse } from "next/server";
import { authOptions } from "../auth/auth";

export async function POST(req: Request) {
  const user: User = await req.json();
  const session = await getServerSession(authOptions);

  if (!session || session.user.role != "ADMIN") {
    return NextResponse.json({ error: "Unauthorized" }, { status: 401 });
  }

  try {
    const res = await api.post("/auth/register", {
      email: user.email,
      password: user.password,
      nome: user.name,
      periculosidade: user.periculosidade,
      insalubridade: user.insalubridade,
      cargo: user.cargo,
      cpf: user.cpf,
      salarioBruto: user.salarioBruto,
      role: "USER",
    });

    return NextResponse.json(res.data);
  } catch (error: any) {
    return NextResponse.json(
      { error: error.response.data.message },
      { status: error.response?.status || 500 }
    );
  }
}
