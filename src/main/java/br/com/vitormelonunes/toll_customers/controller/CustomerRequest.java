package br.com.vitormelonunes.toll_customers.controller;

public record CustomerRequest(
        String name,
        String identificationDocument,
        String licensePlateNumber,
        String phone
) {
}
