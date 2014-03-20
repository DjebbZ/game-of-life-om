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
                (js/console.log "IIniState")
                nil)
    om/IRender
    (render [this]
            (js/console.log "IRender")
            (dom/div nil
             (dom/h1 nil (:text data))
             (om/build my-subwidget (:subtitle data)))
            )

    om/IWillMount
    (will-mount [_]
                (js/console.log "IWillUnmount"))
    om/IDidMount
    (did-mount [_]
               (js/console.log "IDidMount"))
    om/IWillUpdate
    (will-update [_ _ _]
                 (js/console.log "IWillUpdate"))
    om/IDidUpdate
    (did-update [_ _ _]
                (js/console.log "IDidUpdate"))
    om/IWillUnmount
    (will-unmount [_]
                     (js/console.log "IWillUnmount"))
    om/IShouldUpdate
    (should-update [_ _ _]
                   (js/console.log "IShouldUpdate")
                   true)))

(defn my-subwidget [data owner]
  (reify
    om/IRender
    (render [_]
            (js/console.log "my-subwidget IRender")
            (dom/h2 nil data))))

(om/root
  my-widget
  app-state
  {:target (. js/document (getElementById "app"))})

(swap! app-state (fn [state]
                   (assoc state :text "Test swa7")))

