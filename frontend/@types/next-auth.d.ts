import { type DefaultSession } from 'next-auth';
import 'next-auth/jwt';

declare module 'next-auth' {
  interface User {
    token?: string;
  }

  interface Session {
    user: {
      role?: string;
    } & DefaultSession['user'];
    idToken?: string;
  }
}

declare module 'next-auth/jwt' {
  interface JWT {
    idToken?: string;
  }
}