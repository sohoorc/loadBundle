/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 * @lint-ignore-every XPLATJSCOPYRIGHT1
 */

import React, { Component } from 'react';
import { StyleSheet, Text, View, Button, NativeModules } from 'react-native';

export default class App extends Component {
  jump = () => {
    NativeModules.RNBridge.jumpNativePage('http://10.10.1.155:62069/bundleTest.bundle', 'bundleTest')
    // NativeModules.RNBridge.jumpNativePage('123', 'loadBundle')
    // NativeModules.RNBridge.toast('弹出一个弹窗', 1)
  }

  downloadBundle = () => {
    NativeModules.RNBridge.downloadBundle()
  }

  render() {
    console.log(NativeModules)
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}>RN测试加载Bundle包并跳转！</Text>
        <View>
          <Button title="跳转到RNBrowser" onPress={this.jump}></Button>
          <Button title="下载bundle" onPress={this.downloadBundle}></Button>
        </View>
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
