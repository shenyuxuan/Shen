package com.bw.shen;

import com.bw.shen.bean.DetailBean;
import com.bw.shen.bean.Result;
import com.bw.shen.bean.Search;
import com.bw.shen.bean.Shops;
import com.bw.shen.bean.SynchronousBean;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PUT;
import retrofit2.http.Query;

/**
 * Created by lenovo on 2019-1-1.
 */

public interface RetrofitApi {

    //首页
    @GET("small/commodity/v1/commodityList")
    Observable<Shops> ShopRx();

    //商品详情

    @GET("small/commodity/v1/findCommodityDetailsById")
    Observable<DetailBean> ShopDetail(@Header("userId") int userId, @Header("sessionId") String sessionId, @Query("commodityId") int commodityId);


    //根据关键字查询
    @GET("small/commodity/v1/findCommodityByKeyword")
    Observable<Result<List<Search>>> Search(@Query("keyword") String keyword,@Query("page") int page,@Query("count") int count);
//
//   //展示订单
//    @GET("small/order/verify/v1/findOrderListByStatus")
//    Observable<AllIndent> Indent(@Header("userId") int userId,@Header("sessionId") String sessionId,@Query("status") int status,@Query("page") int page,@Query("count") int count);

    //添加购物车
    @FormUrlEncoded
    @PUT("small/order/verify/v1/syncShoppingCart")
    Observable<SynchronousBean> synchronous(@Header("userId") int userId,@Header("sessionId") String sessionId,@Field("data") String data);



}
