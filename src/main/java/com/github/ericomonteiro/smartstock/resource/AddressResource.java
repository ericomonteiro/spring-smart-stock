package com.github.ericomonteiro.smartstock.resource;

import com.github.ericomonteiro.smartstock.integration.viavep.ViaCepClient;
import com.github.ericomonteiro.smartstock.integration.viavep.dto.ViaCepAddressDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressResource {
    private final ViaCepClient viaCepClient;

    @GetMapping("/{zipCode}")
    public ViaCepAddressDto getAddressByZipCode(@PathVariable String zipCode) {
        return viaCepClient.getAddress(zipCode);
    }

}
