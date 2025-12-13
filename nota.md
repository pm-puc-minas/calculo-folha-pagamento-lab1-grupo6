2 -  Consegui subir a aplicação, mas não consegui logar com o usuário e senha disponibilizados. Erro de 
  
```
[cause]: TypeError: Invalid URL
      at Object.authorize (app/api/auth/auth.ts:14:29)
    12 |       async authorize(credentials) {
    13 |         try {
  > 14 |           const res = await fetch(`${process.env.NEXT_PUBLIC_API_URL}/auth/login`, {
       |                             ^
    15 |             method: "POST",
    16 |             headers: { "Content-Type": "application/json" },
    17 |             body: JSON.stringify({ {
    code: 'ERR_INVALID_URL',
    input: 'undefined/auth/login'
  }
}
```