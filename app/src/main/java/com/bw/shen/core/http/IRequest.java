package com.bw.shen.core.http;

import com.bw.shen.bean.Banner;
import com.bw.shen.bean.Circle;
import com.bw.shen.bean.Foot;
import com.bw.shen.bean.IndentAll;
import com.bw.shen.bean.IndentBean;
import com.bw.shen.bean.MyAddress;
import com.bw.shen.bean.Result;
import com.bw.shen.bean.ShopCarBean;
import com.bw.shen.bean.UserInfo;

import java.util.List;

import io.reactivex.Observable;
import okhttp3.MultipartBody;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019-1-7.
 */

public interface IRequest {
    /**
     * 注册
     */
    @FormUrlEncoded
    @POST("user/v1/register")
    Observable<Result> register(@Field("phone") String m, @Field("pwd") String p);

    /**
     * 登录
     */
    @FormUrlEncoded
    @POST("user/v1/login")
    Observable<Result<UserInfo>> login(@Field("phone") String m, @Field("pwd") String p);


    /**
     * banner
     */
    @GET("commodity/v1/bannerShow")
    Observable<Result<List<Banner>>> bannerShow();
    /**
     * 圈子
     */
    @GET("circle/v1/findCircleList")
    Observable<Result<List<Circle>>> findCircleList(
            @Header("userId") long userId,
            @Header("sessionId")String sessionId,
            @Query("page")int page,
            @Query("count")int count);

    /**
     * 圈子
     */
    @FormUrlEncoded
    @POST("user/findCircle/{uid}")
    Observable<Result<List<Circle>>> findCircle(
            @Path("uid") int uid,
            @Field("page") int page,
            @Field("count") int count);

    //展示购物车
    //http://172.17.8.100/
    @GET("order/verify/v1/findShoppingCart")
    Observable<Result<List<ShopCarBean>>> Shopcar(@Header("userId") long userId,
                                                  @Header("sessionId")String sessionId);


    //展示订单
    //http://172.17.8.100/?status=0&page=1&count=5
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<Result<List<IndentAll>>> Indent(@Header("userId") long userId,
                                                   @Header("sessionId") String sessionId,
                                                   @Query("status") int status,
                                                   @Query("page") int page,
                                                   @Query("count") int count);

    //代付款
    @GET("order/verify/v1/findOrderListByStatus")
    Observable<Result<List<IndentAll>>> IndentPay(@Header("userId") long userId,
                                               @Header("sessionId") String sessionId,
                                               @Query("status") int status,
                                               @Query("page") int page,
                                               @Query("count") int count);
    //我的足记
    //http://172.17.8.100/small/commodity/verify/v1/browseList
    @GET("commodity/verify/v1/browseList")
    Observable<Result<List<Foot>>> getmyfoot(@Header("userId") long userId,
                                             @Header("sessionId") String sessionId,
                                             @Query("page") int page,
                                             @Query("count") int count);

    /**
     * 发布圈子
     */
    @POST("circle/verify/v1/releaseCircle")
    Observable<Result> releaseCircle(@Header("userId") long userId,
                                     @Header("sessionId")String sessionId,
                                     @Body MultipartBody body);

    /**
     * 我的收货地址
     *
     */
    @GET("user/verify/v1/receiveAddressList")
    Observable<Result<List<MyAddress>>> getmyAddress(@Header("userId") long userId,
                                                     @Header("sessionId") String sessionId);

    /**
     * 新增收货地址
     * @return
     */
    @POST("user/verify/v1/addReceiveAddress")
    @FormUrlEncoded
    Observable<Result> getaddress(@Header("userId") String userId,
                                  @Header("sessionId") String sessionId,
                                  @Field("realName") String realName,
                                  @Field("phone") String phone,
                                  @Field("address") String address,
                                  @Field("zipCode") String zipCode);

}
