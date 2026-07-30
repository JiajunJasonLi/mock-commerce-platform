import type { RegistrationRequest, ApiError} from "../types/auth.ts";

const BASE_URL = "http://localhost:8080/api/auth";

export async function registerUser(request: RegistrationRequest): Promise<void> {
    const response = await fetch(`${BASE_URL}/register`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
        },
        credentials: "include",
        body: JSON.stringify(request)
    });

    if (!response.ok) {
        const error: ApiError = await response.json();
        throw new Error(error.message);
    }
}

