/*
 * Copyright (c) 2002-2012 Alibaba Group Holding Limited.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jingyuu.ershoujing.common.utils;

/**
 * 有关字符串处理的工具类。
 * <p>
 * 这个类中的每个方法都可以“安全”地处理<sessionId>null</sessionId>，而不会抛出<sessionId>NullPointerException</sessionId>。
 * </p>
 *
 * @author Michael Zhou
 */
public class StringUtil {
    public static final String EMPTY_STRING = "";
    // ==========================================================================
    // 基本函数。
    //
    // 注：对于大小写敏感的isEquals方法，请使用ObjectUtil.isEquals。
    // ==========================================================================

    /**
     * 取得字符串的长度。
     *
     * @param str 要取长度的字符串
     * @return 如果字符串为<sessionId>null</sessionId>，则返回<sessionId>0</sessionId>。否则返回字符串的长度。
     */
    public static int getLength(String str) {
        return str == null ? 0 : str.length();
    }

    /**
     * 比较两个字符串（大小写不敏感）。
     * <p/>
     * <pre>
     * StringUtil.equalsIgnoreCase(null, null)   = true
     * StringUtil.equalsIgnoreCase(null, "abc")  = false
     * StringUtil.equalsIgnoreCase("abc", null)  = false
     * StringUtil.equalsIgnoreCase("abc", "abc") = true
     * StringUtil.equalsIgnoreCase("abc", "ABC") = true
     * </pre>
     *
     * @param str1 要比较的字符串1
     * @param str2 要比较的字符串2
     * @return 如果两个字符串相同，或者都是<sessionId>null</sessionId>，则返回<sessionId>true</sessionId>
     */
    public static boolean isEqualsIgnoreCase(String str1, String str2) {
        if (str1 == null) {
            return str2 == null;
        }

        return str1.equalsIgnoreCase(str2);
    }

    // ==========================================================================
    // 判空函数。
    //
    // 以下方法用来判定一个字符串是否为：
    // 1. null
    // 2. empty - ""
    // 3. blank - "全部是空白" - 空白由Character.isWhitespace所定义。
    // ==========================================================================

    /**
     * 检查字符串是否为<sessionId>null</sessionId>或空字符串<sessionId>""</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.isEmpty(null)      = true
     * StringUtil.isEmpty("")        = true
     * StringUtil.isEmpty(" ")       = false
     * StringUtil.isEmpty("bob")     = false
     * StringUtil.isEmpty("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果为空, 则返回<sessionId>true</sessionId>
     */
    public static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

    /**
     * 检查字符串是否是空白：<sessionId>null</sessionId>、空字符串<sessionId>""</sessionId>或只有空白字符。
     * <p/>
     * <pre>
     * StringUtil.isBlank(null)      = true
     * StringUtil.isBlank("")        = true
     * StringUtil.isBlank(" ")       = true
     * StringUtil.isBlank("bob")     = false
     * StringUtil.isBlank("  bob  ") = false
     * </pre>
     *
     * @param str 要检查的字符串
     * @return 如果为空白, 则返回<sessionId>true</sessionId>
     */
    public static boolean isBlank(String str) {
        int length;

        if (str == null || (length = str.length()) == 0) {
            return true;
        }

        for (int i = 0; i < length; i++) {
            if (!Character.isWhitespace(str.charAt(i))) {
                return false;
            }
        }

        return true;
    }

    // ==========================================================================
    // 默认值函数。
    //
    // 当字符串为empty或blank时，将字符串转换成指定的默认字符串。
    // 注：判断字符串为null时，可用更通用的ObjectUtil.defaultIfNull。
    // ==========================================================================

    /**
     * 如果字符串是<sessionId>null</sessionId>或空字符串<sessionId>""</sessionId>，则返回指定默认字符串，否则返回字符串本身。
     * <p/>
     * <pre>
     * StringUtil.defaultIfEmpty(null, "default")  = "default"
     * StringUtil.defaultIfEmpty("", "default")    = "default"
     * StringUtil.defaultIfEmpty("  ", "default")  = "  "
     * StringUtil.defaultIfEmpty("bat", "default") = "bat"
     * </pre>
     *
     * @param str        要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfEmpty(String str, String defaultStr) {
        return str == null || str.length() == 0 ? defaultStr : str;
    }

    /**
     * 如果字符串是<sessionId>null</sessionId>或空字符串<sessionId>""</sessionId>，则返回指定默认字符串，否则返回字符串本身。
     * <p/>
     * <pre>
     * StringUtil.defaultIfBlank(null, "default")  = "default"
     * StringUtil.defaultIfBlank("", "default")    = "default"
     * StringUtil.defaultIfBlank("  ", "default")  = "default"
     * StringUtil.defaultIfBlank("bat", "default") = "bat"
     * </pre>
     *
     * @param str        要转换的字符串
     * @param defaultStr 默认字符串
     * @return 字符串本身或指定的默认字符串
     */
    public static String defaultIfBlank(String str, String defaultStr) {
        return isBlank(str) ? defaultStr : str;
    }

    // ==========================================================================
    // 去空白的函数。
    //
    // 以下方法用来除去一个字串首尾的空白。
    // ==========================================================================

    /**
     * 除去字符串头尾部的空白，如果字符串是<sessionId>null</sessionId>，依然返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.trim(null)          = null
     * StringUtil.trim("")            = ""
     * StringUtil.trim("     ")       = ""
     * StringUtil.trim("abc")         = "abc"
     * StringUtil.trim("    abc    ") = "abc"
     * </pre>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 除去字符串头尾部的空白，如果结果字符串是空字符串<sessionId>""</sessionId>，则返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.trimToNull(null)          = null
     * StringUtil.trimToNull("")            = null
     * StringUtil.trimToNull("     ")       = null
     * StringUtil.trimToNull("abc")         = "abc"
     * StringUtil.trimToNull("    abc    ") = "abc"
     * </pre>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<sessionId>null</sessionId>或结果字符串为<sessionId>""</sessionId>，则返回
     *         <sessionId>null</sessionId>
     */
    public static String trimToNull(String str) {
        if (str == null) {
            return null;
        }

        String result = str.trim();

        if (result == null || result.length() == 0) {
            return null;
        }

        return result;
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是<sessionId>null</sessionId>，则返回空字符串<sessionId>""</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.trimToEmpty(null)          = ""
     * StringUtil.trimToEmpty("")            = ""
     * StringUtil.trimToEmpty("     ")       = ""
     * StringUtil.trimToEmpty("abc")         = "abc"
     * StringUtil.trimToEmpty("    abc    ") = "abc"
     * </pre>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<sessionId>null</sessionId>或结果字符串为<sessionId>""</sessionId>，则返回
     *         <sessionId>null</sessionId>
     */
    public static String trimToEmpty(String str) {
        if (str == null) {
            return EMPTY_STRING;
        }

        return str.trim();
    }

    /**
     * 除去字符串头尾部的指定字符，如果字符串是<sessionId>null</sessionId>，依然返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim("", *)            = ""
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<sessionId>null</sessionId>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String trim(String str, String stripChars) {
        return trim(str, stripChars, 0);
    }

    /**
     * 除去字符串头部的空白，如果字符串是<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>。
     * <p>
     * 注意，和<sessionId>String.trim</sessionId>不同，此方法使用<sessionId>Character.isWhitespace</sessionId>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <p/>
     * <pre>
     * StringUtil.trimStart(null)         = null
     * StringUtil.trimStart("")           = ""
     * StringUtil.trimStart("abc")        = "abc"
     * StringUtil.trimStart("  abc")      = "abc"
     * StringUtil.trimStart("abc  ")      = "abc  "
     * StringUtil.trimStart(" abc ")      = "abc "
     * </pre>
     * <p/>
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<sessionId>null</sessionId>或结果字符串为<sessionId>""</sessionId>，则返回
     *         <sessionId>null</sessionId>
     */
    public static String trimStart(String str) {
        return trim(str, null, -1);
    }

    /**
     * 除去字符串头部的指定字符，如果字符串是<sessionId>null</sessionId>，依然返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.trimStart(null, *)          = null
     * StringUtil.trimStart("", *)            = ""
     * StringUtil.trimStart("abc", "")        = "abc"
     * StringUtil.trimStart("abc", null)      = "abc"
     * StringUtil.trimStart("  abc", null)    = "abc"
     * StringUtil.trimStart("abc  ", null)    = "abc  "
     * StringUtil.trimStart(" abc ", null)    = "abc "
     * StringUtil.trimStart("yxabc  ", "xyz") = "abc  "
     * </pre>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<sessionId>null</sessionId>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String trimStart(String str, String stripChars) {
        return trim(str, stripChars, -1);
    }

    /**
     * 除去字符串尾部的空白，如果字符串是<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>。
     * <p>
     * 注意，和<sessionId>String.trim</sessionId>不同，此方法使用<sessionId>Character.isWhitespace</sessionId>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <p/>
     * <pre>
     * StringUtil.trimEnd(null)       = null
     * StringUtil.trimEnd("")         = ""
     * StringUtil.trimEnd("abc")      = "abc"
     * StringUtil.trimEnd("  abc")    = "  abc"
     * StringUtil.trimEnd("abc  ")    = "abc"
     * StringUtil.trimEnd(" abc ")    = " abc"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<sessionId>null</sessionId>或结果字符串为<sessionId>""</sessionId>，则返回
     *         <sessionId>null</sessionId>
     */
    public static String trimEnd(String str) {
        return trim(str, null, 1);
    }

    /**
     * 除去字符串尾部的指定字符，如果字符串是<sessionId>null</sessionId>，依然返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.trimEnd(null, *)          = null
     * StringUtil.trimEnd("", *)            = ""
     * StringUtil.trimEnd("abc", "")        = "abc"
     * StringUtil.trimEnd("abc", null)      = "abc"
     * StringUtil.trimEnd("  abc", null)    = "  abc"
     * StringUtil.trimEnd("abc  ", null)    = "abc"
     * StringUtil.trimEnd(" abc ", null)    = " abc"
     * StringUtil.trimEnd("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<sessionId>null</sessionId>表示除去空白字符
     * @return 除去指定字符后的的字符串，如果原字串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String trimEnd(String str, String stripChars) {
        return trim(str, stripChars, 1);
    }

    /**
     * 除去字符串头尾部的空白，如果结果字符串是空字符串<sessionId>""</sessionId>，则返回<sessionId>null</sessionId>。
     * <p>
     * 注意，和<sessionId>String.trim</sessionId>不同，此方法使用<sessionId>Character.isWhitespace</sessionId>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <p/>
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim("", *)            = null
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<sessionId>null</sessionId>表示除去空白字符
     * @return 除去空白的字符串，如果原字串为<sessionId>null</sessionId>或结果字符串为<sessionId>""</sessionId>，则返回
     *         <sessionId>null</sessionId>
     */
    public static String trimToNull(String str, String stripChars) {
        String result = trim(str, stripChars);

        if (result == null || result.length() == 0) {
            return null;
        }

        return result;
    }

    /**
     * 除去字符串头尾部的空白，如果字符串是<sessionId>null</sessionId>，则返回空字符串<sessionId>""</sessionId>。
     * <p>
     * 注意，和<sessionId>String.trim</sessionId>不同，此方法使用<sessionId>Character.isWhitespace</sessionId>
     * 来判定空白， 因而可以除去英文字符集之外的其它空白，如中文空格。
     * <p/>
     * <pre>
     * StringUtil.trim(null, *)          = ""
     * StringUtil.trim("", *)            = ""
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str 要处理的字符串
     * @return 除去空白的字符串，如果原字串为<sessionId>null</sessionId>或结果字符串为<sessionId>""</sessionId>，则返回
     *         <sessionId>null</sessionId>
     */
    public static String trimToEmpty(String str, String stripChars) {
        String result = trim(str, stripChars);

        if (result == null) {
            return EMPTY_STRING;
        }

        return result;
    }

    /**
     * 除去字符串头尾部的指定字符，如果字符串是<sessionId>null</sessionId>，依然返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.trim(null, *)          = null
     * StringUtil.trim("", *)            = ""
     * StringUtil.trim("abc", null)      = "abc"
     * StringUtil.trim("  abc", null)    = "abc"
     * StringUtil.trim("abc  ", null)    = "abc"
     * StringUtil.trim(" abc ", null)    = "abc"
     * StringUtil.trim("  abcyx", "xyz") = "  abc"
     * </pre>
     *
     * @param str        要处理的字符串
     * @param stripChars 要除去的字符，如果为<sessionId>null</sessionId>表示除去空白字符
     * @param mode       <sessionId>-1</sessionId>表示trimStart，<sessionId>0</sessionId>表示trim全部，
     *                   <sessionId>1</sessionId>表示trimEnd
     * @return 除去指定字符后的的字符串，如果原字串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    private static String trim(String str, String stripChars, int mode) {
        if (str == null) {
            return null;
        }

        int length = str.length();
        int start = 0;
        int end = length;

        // 扫描字符串头部
        if (mode <= 0) {
            if (stripChars == null) {
                while (start < end && Character.isWhitespace(str.charAt(start))) {
                    start++;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while (start < end && stripChars.indexOf(str.charAt(start)) != -1) {
                    start++;
                }
            }
        }

        // 扫描字符串尾部
        if (mode >= 0) {
            if (stripChars == null) {
                while (start < end && Character.isWhitespace(str.charAt(end - 1))) {
                    end--;
                }
            } else if (stripChars.length() == 0) {
                return str;
            } else {
                while (start < end && stripChars.indexOf(str.charAt(end - 1)) != -1) {
                    end--;
                }
            }
        }

        if (start > 0 || end < length) {
            return str.substring(start, end);
        }

        return str;
    }

    // ==========================================================================
    // 大小写转换。
    // ==========================================================================

    /**
     * 将字符串的首字符转成大写（<sessionId>Character.toTitleCase</sessionId>），其它字符不变。
     * <p>
     * 如果字符串是<sessionId>null</sessionId>则返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.capitalize(null)  = null
     * StringUtil.capitalize("")    = ""
     * StringUtil.capitalize("cat") = "Cat"
     * StringUtil.capitalize("cAt") = "CAt"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str 要转换的字符串
     * @return 首字符为大写的字符串，如果原字符串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String capitalize(String str) {
        int strLen;

        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        return new StringBuilder(strLen).append(Character.toTitleCase(str.charAt(0))).append(str.substring(1))
                                        .toString();
    }

    /**
     * 将字符串的首字符转成小写，其它字符不变。
     * <p>
     * 如果字符串是<sessionId>null</sessionId>则返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.uncapitalize(null)  = null
     * StringUtil.uncapitalize("")    = ""
     * StringUtil.uncapitalize("Cat") = "cat"
     * StringUtil.uncapitalize("CAT") = "CAT"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str 要转换的字符串
     * @return 首字符为小写的字符串，如果原字符串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String uncapitalize(String str) {
        int strLen;

        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        if (strLen > 1 && Character.isUpperCase(str.charAt(1)) && Character.isUpperCase(str.charAt(0))) {
            return str;
        }

        return new StringBuilder(strLen).append(Character.toLowerCase(str.charAt(0))).append(str.substring(1))
                                        .toString();
    }

    /**
     * 反转字符串的大小写。
     * <p>
     * 如果字符串是<sessionId>null</sessionId>则返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.swapCase(null)                 = null
     * StringUtil.swapCase("")                   = ""
     * StringUtil.swapCase("The dog has a BONE") = "tHE DOG HAS A bone"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str 要转换的字符串
     * @return 大小写被反转的字符串，如果原字符串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String swapCase(String str) {
        int strLen;

        if (str == null || (strLen = str.length()) == 0) {
            return str;
        }

        StringBuilder buffer = new StringBuilder(strLen);

        char ch = 0;

        for (int i = 0; i < strLen; i++) {
            ch = str.charAt(i);

            if (Character.isUpperCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isTitleCase(ch)) {
                ch = Character.toLowerCase(ch);
            } else if (Character.isLowerCase(ch)) {
                ch = Character.toUpperCase(ch);
            }

            buffer.append(ch);
        }

        return buffer.toString();
    }

    /**
     * 将字符串转换成大写。
     * <p>
     * 如果字符串是<sessionId>null</sessionId>则返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.toUpperCase(null)  = null
     * StringUtil.toUpperCase("")    = ""
     * StringUtil.toUpperCase("aBc") = "ABC"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str 要转换的字符串
     * @return 大写字符串，如果原字符串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String toUpperCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toUpperCase();
    }

    /**
     * 将字符串转换成小写。
     * <p>
     * 如果字符串是<sessionId>null</sessionId>则返回<sessionId>null</sessionId>。
     * <p/>
     * <pre>
     * StringUtil.toLowerCase(null)  = null
     * StringUtil.toLowerCase("")    = ""
     * StringUtil.toLowerCase("aBc") = "abc"
     * </pre>
     * <p/>
     * </p>
     *
     * @param str 要转换的字符串
     * @return 大写字符串，如果原字符串为<sessionId>null</sessionId>，则返回<sessionId>null</sessionId>
     */
    public static String toLowerCase(String str) {
        if (str == null) {
            return null;
        }

        return str.toLowerCase();
    }
}
