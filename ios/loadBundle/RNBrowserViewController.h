//
//  RNBrowserViewController.h
//  loadBundleRN
//
//  Created by  WebDeveloper on 2019/5/27.
//  Copyright © 2019 Facebook. All rights reserved.
//

#import <UIKit/UIKit.h>

NS_ASSUME_NONNULL_BEGIN

@interface RNBrowserViewController : UIViewController
@property (nonatomic,copy) NSURL *bundlePath;
@property (nonatomic,copy) NSString *bundleName;
@end

NS_ASSUME_NONNULL_END
