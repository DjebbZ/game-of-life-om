(ns game-of-life-om.core
  (:require [om.core :as om :include-macros true]
            [om.dom :as dom :include-macros true]))

(enable-console-print!)

(def app-state (atom {:text "Hello world!"
                      :subtitle "Subtitle"}))

(defn my-widget [data owner]
  (reify
    om/IInitState
    (init-state [_]
                (prn "IIniState")
                nil)
    om/IRender
    (render [_]
            (prn "IRender")
            (dom/div nil
             (dom/h1 nil (:text data))
             (om/build my-subwidget (:subtitle data)))
            )

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

(defn my-subwidget [data owner]
  (reify
    om/IRender
    (render [_]
            (prn "my-subwidget IRender")
            (dom/h2 nil data))))

(om/root
  my-widget
  app-state
  {:target (. js/document (getElementById "app"))})

(swap! app-state (fn [state]
                   (assoc state :text "Test swa7")))

