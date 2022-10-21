package study.seo.a4netwroking_quakereportapp.data

import com.google.gson.annotations.SerializedName

data class QuakeProperty(
    @SerializedName("mag")
    val mag: Double,
    @SerializedName("place")
    val place: String,
    @SerializedName("time")
    val time: Long,
    @SerializedName("url")
    val url: String,
) {
    companion object {
        operator fun invoke(property: QuakeProperty): QuakeProperty {
            return with(property) {
                QuakeProperty(mag,
                    if (place.isNullOrEmpty()) {
                        "SomeWhere"
                    } else {
                        place
                    },
                    time,
                    url
                )
            }
        }
    }
}


// @SerializedName으로 일치시켜 주지않을 경우엔 클래스 변수명이 일치해야함
// @SerializedName()로 변수명을 입치시켜주면 클래스 변수명이 달라도 알아서 매핑
//    위처럼 JSON 데이터 속성명, 변수명 + 타입(String, Boolean) 일치필수
//JSON - @SerializedName("속성명")으로 속성명 일치시켜주면 변수명 다르게 사용 가능
//XML - @Element(name="속성명")으로 XML은 @Element 애노테이션 사용
//data class QuakeInfo(
//    val mag: Double,
//    val place: String,
//    val time: Long,
//    val url: String
//) {
//    companion object {
//        operator fun invoke(property: JSONObject): QuakeInfo {
//            return with(property) {
//                QuakeInfo(
//                    getDouble("mag"),
//                    getString("place"),
//                    getLong("time"),
//                    getString("url"),
//                )
//            }
//        }
//    }
//}
