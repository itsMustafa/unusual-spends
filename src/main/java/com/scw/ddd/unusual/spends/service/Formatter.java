package com.scw.ddd.unusual.spends.service;

import com.scw.ddd.unusual.spends.dto.UnusualSpend;

import java.util.List;

public interface Formatter {

    String formatMessage(List<UnusualSpend> unusualSpends, String firstName);

}
