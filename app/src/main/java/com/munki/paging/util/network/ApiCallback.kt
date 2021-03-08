package com.munki.paging.util.network

/**
 * RxRetrofit API Callback
 *
 * RxJava를 이용하여 Retrofit2 작업요청에 대한 인터페이스 정의
 * ApiCallback<DTO> -> Retrofit2 Respnse 데이터를 작성 -> Object와 ArrayList 구분 필수
 *
 * subscribeOn - 사용자가 흐름에 맞는 스레드 생성
 * observeOn - 사용자가 처리된 결과를 전달받는 스레드 생성
 * subscribe - 처리된 결과 [onSuccess(성공) | onError(실패) | onApiComplete(완료)]
 * @author 나비이쁜이
 * @since 2021.03.09
*/
interface ApiCallback<R> {
    fun onSuccess(response: R)  // 성공
    fun onError(msg: String?)   // 실패
    fun onApiComplete()         // 확인
}