package com.jvanila.flutter.shopify.usecases;

import com.jvanila.flutter.pluginarch.PluginContext;
import com.shopapp.gateway.ApiCallback;
import com.shopapp.gateway.entity.Error;
import com.shopapp.shopify.api.ShopifyApi;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import kotlin.Unit;

public class UpdateCustomerSettingsUseCase extends ShopifyCallUseCase {

    private static final String ARG_IS_ACCEPT_MARKETING = "isAcceptMarketing";

    public UpdateCustomerSettingsUseCase(PluginContext<ShopifyApi> pluginContext) {
        super(pluginContext);
    }

    @Override
    protected void call(MethodCall input, final MethodChannel.Result result) {
        boolean isAcceptMarketing = input.argument(ARG_IS_ACCEPT_MARKETING);
        mPluginContext.api.instance.updateCustomerSettings(isAcceptMarketing, new ApiCallback<Unit>() {
            @Override
            public void onResult(Unit unit) {
                result.success(true);
            }

            @Override
            public void onFailure(Error error) {
                System.out.println("onFailure -- " + error);
                result.error("UpdateCustomerSettingsUseCase", error.getMessage(), error);
            }
        });
    }
}
