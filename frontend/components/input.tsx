import { UseFormRegisterReturn } from "react-hook-form";

interface InputProps {
  id: string;
  label: string;
  type: string;
  placeholder: string;
  required?: boolean;
  register?: UseFormRegisterReturn;
  error?: string;
}

export default function Input({
  id,
  label,
  type,
  placeholder,
  required = false,
  register,
  error,
}: InputProps) {
  return (
    <div className="w-full">
      <label
        htmlFor="password"
        className="block text-sm font-medium text-gray-700 pb-2"
      >
        {label}
      </label>
      <input
        id={id}
        type={type}
        placeholder={placeholder}
        required={required}
        {...register}
        className="mt-1 block w-full border-b border-b-gray-300 focus:border-indigo-500 focus:ring-indigo-500 sm:text-sm outline-none pb-2 text-black"
      />
      {error && <p className="text-red-500 text-sm mt-1">{error}</p>}
    </div>
  );
}
