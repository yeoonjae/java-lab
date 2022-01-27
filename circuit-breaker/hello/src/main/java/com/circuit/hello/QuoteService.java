package com.circuit.hello;

public interface QuoteService {
  int getQuote();

  int getQuoteFallback(Throwable t);
}
