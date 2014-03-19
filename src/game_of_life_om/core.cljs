(ns game-of-life-om.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:text "Hello world!"}))

(defn my-widget [data owner]
  (reify
    om/IInitState
    (init-state [_]
                (prn "IIniState")
                nil)
    om/IRender
    (render [_]
            (prn "IRender")
            (dom/h1 nil (:text data)))
    om/IWillMount
    (will-mount [_]
                (prn "IWillUnmount"))
    om/IDidMount
    (did-mount [_]
               (prn "IDidMount"))
    om/IWillUpdate
    (will-update [_ _ _]
                 (prn "IWillUpdate"))
    om/IDidUpdate
    (did-update [_ _ _]
                (prn "IDidUpdate"))
    om/IWillUnmount
    (will-unmount [_]
                     (prn "IWillUnmount"))
    om/IShouldUpdate
    (should-update [_ _ _]
                   (prn "IShouldUpdate")
                   true)))

(om/root
  my-widget
  app-state
  {:target (. js/document (getElementById "app"))})

(swap! app-state (fn [state]
                   (assoc state :text "Test swa5")))

