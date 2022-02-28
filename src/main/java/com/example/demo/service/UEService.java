package com.example.demo.service;

import com.example.demo.model.UE;

import java.util.List;

public interface UEService {
    List<UE> allUEs();

    UE getUE(String reference);

    UE saveUE(UE uniteE);

    String deleteUE(Long id);

    UE updateUE(UE uniteE);
}
