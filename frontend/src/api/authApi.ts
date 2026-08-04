import type {RegistrationRequest, ApiError, LoginRequest, ProfileResponse} from "../types/auth.ts";

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

export async function loginUser(request: LoginRequest): Promise<ProfileResponse> {
    const response = await fetch(`${BASE_URL}/login`, {
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

    return response.json() as Promise<ProfileResponse>;
}

export async function userProfile(): Promise<ProfileResponse> {
    const response = await fetch(`http://localhost:8080/api/profile`, {
        method: "GET",
        credentials: "include"
    })

    if (!response.ok) {
        const error: ApiError = await response.json();

        throw new Error(error.message);
    }

    return response.json() as Promise<ProfileResponse>;
}

export async function userLogout() {
    const response = await fetch(`${BASE_URL}/logout`, {
        method: "POST",
        credentials: "include"
    })

    if (!response.ok) {
        throw new Error(`Logout failed with status ${response.status}`);
    }
}

