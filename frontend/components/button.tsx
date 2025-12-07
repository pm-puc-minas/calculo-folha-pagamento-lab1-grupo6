export function Button({ label, onClick, type }: { label: string; onClick?: () => void; type?: "button" | "submit" | "reset" }) {
  return <button className="text-white bg-blue-500 px-8 py-2 rounded cursor-pointer" type={type} onClick={onClick}>{label}</button>;
}