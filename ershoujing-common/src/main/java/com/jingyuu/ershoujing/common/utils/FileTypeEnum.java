package com.jingyuu.ershoujing.common.utils;

/**
 * 文件类型
 */
public enum FileTypeEnum {
    JPEG("jpg", "FFD8FF"),
    PNG("png", "89504E47"),
    GIF("gif", "47494638"),
    TIFF("tif", "49492A00"),
    BMP("bmp", "424D"),
    DWG("dwg", "41433130"),
    HTML("html", "68746D6C3E"),
    RTF("rtf", "7B5C727466"),
    XML("xml", "3C3F786D6C"),
    ZIP("zip", "504B0304"),
    RAR("rar", "52617221"),
    PSD("psd", "38425053"),  //Photoshop (psd)
    EML("eml", "44656C69766572792D646174653A"),  //Email [thorough only] (eml)
    DBX("dbx", "CFAD12FEC5FD746F"),  //Outlook Express (dbx)
    PST("pst", "2142444E"),  //Outlook (pst)
    DOCX("docx", "504B0304"),
    XLSX("xlsx", "504B0304"),
    XLS("xls", "D0CF11E0"),  //MS Word
    DOC("doc", "D0CF11E0"),  //MS Excel 注意：word 和 excel的文件头一样
    MDB("mdb", "5374616E64617264204A"),  //MS Access (mdb)
    WPD("wpd", "FF575043"), //WordPerfect (wpd)
    EPS("eps", "252150532D41646F6265"),
    PS("ps", "252150532D41646F6265"),
    PDF("pdf", "255044462D312E"),  //Adobe Acrobat (pdf)
    QDF("qdf", "AC9EBD8F"),  //Quicken (qdf)
    PWL("pwl", "E3828596"),  //Windows Password (pwl)
    WAV("wav", "57415645"),  //Wave (wav)
    AVI("avi", "41564920"),
    RAM("ram", "2E7261FD"),  //Real Audio (ram)
    RM("rm", "2E524D46"),  //Real Media (rm)
    MPG("mpg", "000001BA"),  //
    MOV("mov", "6D6F6F76"),  //Quicktime (mov)
    ASF("asf", "3026B2758E66CF11"), //Windows Media (asf)
    MID("mid", "4D546864")  //MIDI (mid)
    ;
    /**
     * 扩展名
     */
    private String extension;

    /**
     * 十六进制文件前缀
     */
    private String hexRefixion;

    FileTypeEnum(String extension, String hexRefixion) {
        this.extension = extension;
        this.hexRefixion = hexRefixion;
    }

    public String getHexRefixion() {
        return hexRefixion;
    }

    public String getExtension() {
        return extension;
    }
}
