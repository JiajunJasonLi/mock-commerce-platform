export interface RegistrationRequest {
    username: string,
    email: string,
    password: string;
}

export interface LoginRequest {
    email: string,
    password: string;
}

export interface ProfileResponse {
    username: string,
    email: string,
    membershipTier: string;
}

export interface ApiError {
    code: string,
    message: string;
}