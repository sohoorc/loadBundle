/**
 * Copyright (c) Facebook, Inc. and its affiliates.
 *
 * This source code is licensed under the MIT license found in the
 * LICENSE file in the root directory of this source tree.
 */

#import "AppDelegate.h"

#import <React/RCTBridge.h>
#import <React/RCTBundleURLProvider.h>
#import <React/RCTRootView.h>

@implementation AppDelegate

// - (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
// {
//   NSURL *jsCodeLocation;

//   jsCodeLocation = [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index" fallbackResource:nil];

//   RCTRootView *rootView = [[RCTRootView alloc] initWithBundleURL:jsCodeLocation
//                                                       moduleName:@"loadBundle"
//                                                initialProperties:nil
//                                                    launchOptions:launchOptions];
//   rootView.backgroundColor = [UIColor blackColor];

//   self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
//   UIViewController *rootViewController = [UIViewController new];
//   rootViewController.view = rootView;
//   self.window.rootViewController = rootViewController;
//   [self.window makeKeyAndVisible];
//   return YES;
// }

- (BOOL)application:(UIApplication *)application didFinishLaunchingWithOptions:(NSDictionary *)launchOptions
{
  
  RCTBridge *bridge = [[RCTBridge alloc] initWithDelegate:self launchOptions:launchOptions];
  
  RCTRootView *rootView = [[RCTRootView alloc] initWithBridge:bridge
                                                   moduleName:@"loadBundle"
                                            initialProperties:nil];

  rootView.backgroundColor = [[UIColor alloc] initWithRed:1.0f green:1.0f blue:1.0f alpha:1];

  self.window = [[UIWindow alloc] initWithFrame:[UIScreen mainScreen].bounds];
  
  UIViewController *rootViewController = [UIViewController new];

  rootViewController.view = rootView;
  // 创建navigationcController 并将初始化界面设置成rootViewController
   _nav = [[UINavigationController alloc] initWithRootViewController:rootViewController];
  
  self.window.rootViewController = _nav;
  
  [self.window makeKeyAndVisible];
  return YES;
}

/**
* 
*
*
* 动态加载bundle包代码内容
*
*/

- (NSURL *)sourceURLForBridge:(RCTBridge *)bridge
{
#if DEBUG
  return [[RCTBundleURLProvider sharedSettings] jsBundleURLForBundleRoot:@"index" fallbackResource:nil];
#else
  return [[NSBundle mainBundle] URLForResource:@"main" withExtension:@"jsbundle"];
#endif
}

/*
 bundle包的下载逻辑
 */

//-(void)onClickJump{
//  NSLog(@"测试点击");
//  RNViewController *rnView = [[RNViewController alloc] init];
//  rnView.bundlePath = _bundlePath;
//  [self.navigationController pushViewController:rnView animated:YES];
//}

-(void)downloadBundle{
  NSLog(@"开始下载数据++++++++++++++++++++++++++++++++++");
  NSURL *url = [NSURL URLWithString:@"http://10.10.1.155:5000/ios.bundle.js"];
  NSURLSessionConfiguration *defaultConfig = [NSURLSessionConfiguration defaultSessionConfiguration];
  
  NSURLSession *session = [NSURLSession sessionWithConfiguration:defaultConfig delegate:self delegateQueue:[NSOperationQueue mainQueue]];
  
  NSURLSessionDownloadTask *downloadTask = [session downloadTaskWithURL:url];
  
  [downloadTask resume];
}

//- (void)URLSession:(NSURLSession *)session downloadTask:(NSURLSessionDownloadTask *)downloadTask didResumeAtOffset:(int64_t)fileOffset expectedTotalBytes:(int64_t)expectedTotalBytes{
////     NSLog(@"进度=%i",expectedTotalBytes);
//}

- (void)URLSession:(NSURLSession *)session downloadTask:(NSURLSessionDownloadTask *)downloadTask didWriteData:(int64_t)bytesWritten totalBytesWritten:(int64_t)totalBytesWritten totalBytesExpectedToWrite:(int64_t)totalBytesExpectedToWrite{
  float progress = totalBytesWritten*1.0/totalBytesExpectedToWrite;
//  [self.progressView setProgress:progress animated:TRUE];
  NSLog(@"%@",downloadTask.response);
  NSLog(@"进度=%f",progress);
  
  NSLog(@"接收:%lld字节，已下载%lld字节，等待：%lld字节",bytesWritten,totalBytesWritten,totalBytesExpectedToWrite);
  
}

//-(void)URLsession:(NSURLSession *)session downloadTask:(NSURLSessionDownloadTask *)downloadTask didWriteData:(int64_t)bytesWritten totalBytesWritten:(int64_t)totalBytesWritten totalBytesExpectedToWrite:(int64_t)totalBytesExpectedToWrite{
//
//
//
//}

-(void)URLSession:(NSURLSession *)session downloadTask:(NSURLSessionDownloadTask *)downloadTask didFinishDownloadingToURL:(NSURL *)location{
  NSLog(@"临时文件:%@\n",location);
  
  NSString *downloadsDir = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, TRUE) objectAtIndex:0];
  
  NSString *downloadStrPath = [downloadsDir stringByAppendingString:@"/index.bundle.ios.js"];
  
  NSURL *downloadURLPath = [NSURL fileURLWithPath:downloadStrPath];
  
  NSFileManager *fileManager = [NSFileManager defaultManager];
  
  NSError *error = nil;
  
  if([fileManager fileExistsAtPath:downloadStrPath]){
    [fileManager removeItemAtPath:downloadStrPath error:&error];
    if(error){
      NSLog(@"删除文件失败:%@",error.localizedDescription);
    }
  }
  
  error = nil;
  
  if([fileManager moveItemAtURL:location toURL:downloadURLPath error:&error]){
    NSLog(@"文件保存：%@",downloadURLPath);
    
    _bundlePath = downloadURLPath;
  }else{
    NSLog(@"文件保存失败");
  }
}


@end
