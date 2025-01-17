/*
 * Copyright Elasticsearch B.V. and/or licensed to Elasticsearch B.V. under one
 * or more contributor license agreements. Licensed under the Elastic License
 * 2.0; you may not use this file except in compliance with the Elastic License
 * 2.0.
 */

package org.elasticsearch.xpack.apmdata;

import org.elasticsearch.common.io.Streams;
import org.elasticsearch.xpack.core.template.TemplateUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ResourceUtils {

    public static final String APM_TEMPLATE_VERSION_VARIABLE = "xpack.apmdata.template.version";

    static byte[] loadVersionedResourceUTF8(String name, int version) {
        try {
            String content = loadResource(name);
            content = TemplateUtils.replaceVariable(content, APM_TEMPLATE_VERSION_VARIABLE, String.valueOf(version));
            return content.getBytes(StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    static String loadResource(String name) throws IOException {
        InputStream is = APMIndexTemplateRegistry.class.getResourceAsStream(name);
        if (is == null) {
            throw new IOException("Resource [" + name + "] not found in classpath.");
        }
        return Streams.readFully(is).utf8ToString();
    }
}
