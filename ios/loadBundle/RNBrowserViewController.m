//
//  RNBrowserViewController.m
//  loadBundleRN
//
//  Created by  WebDeveloper on 2019/5/27.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import "RNBrowserViewController.h"
#import <React/RCTRootView.h>
@interface RNBrowserViewController ()

@end

@implementation RNBrowserViewController

- (void)viewDidLoad {
    [super viewDidLoad];
  
  self.view.backgroundColor = [UIColor whiteColor];
  // Do any additional setup after loading the view.
  
  NSLog(@"跳转到RN展示页展示RN++++++++++++++++++++++++++++++++++++++%@",self.bundlePath);
  
  NSString *bundleUrl = [self.bundlePath absoluteString];
  
  NSString *bundleName = self.bundleName ;
  
  NSURL *jsCodeLocation = [NSURL URLWithString:bundleUrl];
  
  RCTRootView *rootView =
  [[RCTRootView alloc] initWithBundleURL: jsCodeLocation
                              moduleName: bundleName
                       initialProperties: nil
                           launchOptions: nil];
  rootView.frame = CGRectMake(0, 0, [[UIScreen mainScreen] bounds].size.width, [[UIScreen mainScreen] bounds].size.height);
  rootView.backgroundColor = UIColor.whiteColor;
  
  [self.view addSubview:rootView];
}

/*
#pragma mark - Navigation

// In a storyboard-based application, you will often want to do a little preparation before navigation
- (void)prepareForSegue:(UIStoryboardSegue *)segue sender:(id)sender {
    // Get the new view controller using [segue destinationViewController].
    // Pass the selected object to the new view controller.
}
*/

@end
