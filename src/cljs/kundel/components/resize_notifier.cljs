(ns kundel.components.resize-notifier
  (:require [goog.string :as gstring]
            [goog.string.format]
            [re-frame.core :as rf]
            [re-frame.subs :as subs]
            [reagent.core :as r]
            [kundel.libs.media-size :as ms]
            [debux.cs.core :refer-macros [clog dbg break]]))

(defn -match-media [media-format]
  "Returns 'true' if CSS 'media-format' matches device"
  (.-matches (.matchMedia js/window (gstring/format "(max-width: %dem)"
                                                    (:max-width (media-format ms/responsive-breakpoint))))))

(defn -get-responsive-breakpoint []
  "Returns a responsive breakpoint as integer: 1 for :mobile, 2 for :tablet 3 for :computer 4 for :large 5 for :wide.  These are configured in iceland.config.responsive-breakpoint."
  (cond
    (-match-media :mobile) :mobile
    (-match-media :tablet) :tablet
    (-match-media :computer) :computer
    (-match-media :large) :large
    :else :wide))

(defn dispatch-sync-new-size []
  (rf/dispatch-sync [:ev:update-current-device-media-format (-get-responsive-breakpoint)]))

(defn -add-listener [this]
  (. js/window addEventListener "resize" dispatch-sync-new-size))



(defn render []
  (r/create-class
    {:reagent-render #(do false)
     :component-did-mount -add-listener}))
