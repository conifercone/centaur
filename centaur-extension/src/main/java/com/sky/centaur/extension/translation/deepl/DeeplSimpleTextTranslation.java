/*
 * Copyright (c) 2024-2024, kaiyu.shan@outlook.com.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.sky.centaur.extension.translation.deepl;

import com.deepl.api.Translator;
import com.sky.centaur.basis.kotlin.tools.SecurityContextUtil;
import com.sky.centaur.extension.translation.SimpleTextTranslation;
import java.util.Optional;
import org.jetbrains.annotations.NotNull;
import org.springframework.util.StringUtils;

/**
 * deepl简单文本翻译
 *
 * @author kaiyu.shan
 * @since 1.0.3
 */
public class DeeplSimpleTextTranslation implements SimpleTextTranslation {

  private final Translator deeplTranslator;

  public DeeplSimpleTextTranslation(Translator deeplTranslator) {
    this.deeplTranslator = deeplTranslator;
  }

  @Override
  public String translate(String text, @NotNull String targetLanguage) throws Exception {
    return deeplTranslator.translateText(text, null, targetLanguage).getText();
  }

  @Override
  public Optional<String> translateToAccountLanguageIfPossible(String text) {
    return Optional.ofNullable(text).filter(StringUtils::hasText)
        .flatMap(res -> SecurityContextUtil.getLoginAccountLanguage()).map(languageEnum -> {
          try {
            return this.translate(text, languageEnum.name().toLowerCase());
          } catch (Exception e) {
            // ignore
          }
          return null;
        });
  }
}
