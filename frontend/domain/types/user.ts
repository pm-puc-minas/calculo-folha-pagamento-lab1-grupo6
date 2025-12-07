export type User = {
  email: string
  password: string
  name: string
  periculosidade: "SIM" | "NAO"
  insalubridade: "NENHUM" | "BAIXO" | "MEDIO" | "ALTO"
  cargo: string
  salarioBruto: number
  cpf: string
}
