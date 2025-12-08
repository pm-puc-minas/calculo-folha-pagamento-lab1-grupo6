export function Button({ label, onClick, type, className }: { label: string; onClick?: () => void; type?: "button" | "submit" | "reset"; className?: string }) {
  return <button className={`px-8 py-2 rounded cursor-pointer ${className || "text-white bg-blue-500"}`} type={type} onClick={onClick}>{label}</button>;
}