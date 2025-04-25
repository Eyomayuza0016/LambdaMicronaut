package com.coom.ath.repository;


import com.coom.ath.model.ParameterStoreDto;
import com.coom.ath.util.ParameterStoreUtil;
import com.coom.ath.util.Util;
import io.micronaut.serde.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.Map;

@Slf4j
public class ParameterStoreRepository {

    public ParameterStoreDto getParameter() {
        ParameterStoreDto parameter = new ParameterStoreDto();

        Map<String, String> parameterSemillero = ParameterStoreUtil.getParameters("/Semillero/capacitacion-semillero/");

        log.info("Mapa: " + Util.object2String(parameterSemillero));

        parameter.setTabla(parameterSemillero.get("/Semillero/capacitacion-semillero/nombreTabla"));
        parameter.setRegion(parameterSemillero.get("/Semillero/capacitacion-semillero/region"));
        parameter.setUrlDynamo(parameterSemillero.get("/Semillero/capacitacion-semillero/dynamoEndpoint"));
        parameter.setArnSecret(parameterSemillero.get("/Semillero/capacitacion-semillero/arnSecret"));
        parameter.setUrlConnection(parameterSemillero.get("/Semillero/capacitacion-semillero/urlConnection"));

        log.info("Parametros correctos {}", Util.object2String(parameter));
        return parameter;
    }
}
