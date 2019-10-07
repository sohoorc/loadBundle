//
//  RNBridge.m
//  loadBundleRN
//
//  Created by  WebDeveloper on 2019/5/27.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "RNBridge.h"
#import <React/RCTBridge.h>
#import "AppDelegate.h"

#import "RNBrowserViewController.h"
//#import "MainViewController.h"


@implementation RNBridge
//@synthesize bridge = _bridge;

RCT_EXPORT_MODULE();

// RN 跳转原生
RCT_EXPORT_METHOD(jumpNativePage:(NSURL *) url andName:(NSString *) bundleName ){
  
  dispatch_async(dispatch_get_main_queue(), ^{
    NSLog(@"跳转到 url%@ andName %@",url,bundleName);
    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];

    RNBrowserViewController *rnBrowser = [[RNBrowserViewController alloc] init];
    
    rnBrowser.bundlePath = url;
    rnBrowser.bundleName = bundleName;
    
    [appDelegate.nav pushViewController:rnBrowser animated:NO];
  });

}

//
RCT_EXPORT_METHOD(back){
  dispatch_async(dispatch_get_main_queue(), ^{
    AppDelegate *appDelegate = (AppDelegate *)[[UIApplication sharedApplication] delegate];

    [appDelegate.nav popViewControllerAnimated:YES];

  });
}

RCT_EXPORT_METHOD(downloadBundle){
  
  dispatch_async(dispatch_get_main_queue(), ^{
    AppDelegate *app= (AppDelegate *)[[UIApplication sharedApplication] delegate];
//    app.downloadBundle();
    
    [app downloadBundle];
  });
  
}


@end
