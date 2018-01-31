(ns kundel.libs.media-size
  (:require [re-frame.subs :as subs]
            [debux.cs.core :refer-macros [clog dbg break]]))

;; Responsive breakpoint map in 'em':  {:mobile {:relative-size-index :min-width # :max-width #} :tablet {..} :computer {..} :large {..} :wide {..}}.  :relative-size-index is useful for integer comparison of relative size.
(def responsive-breakpoint
  {:mobile   {:relative-size-index 1 :min-width 20 :max-width 47.9375}
   :tablet   {:relative-size-index 2 :min-width 48 :max-width 61.9375}
   :computer {:relative-size-index 3 :min-width 62 :max-width 74.9375}
   :large    {:relative-size-index 4 :min-width 75 :max-width 119.9375}
   :wide     {:relative-size-index 5 :min-width 120 :max-width nil}})

(defn one-of [media-formats-coll]
  "'true' if current screen is one of specified 'media-format' entries: in-range"
  (let [desired-indices (set (map #(:relative-size-index (% responsive-breakpoint))
                                  media-formats-coll))
        breakpoint @(subs/subscribe [:env:current-device-media-format])
        screen-index (:relative-size-index (breakpoint responsive-breakpoint))]
    (contains? desired-indices screen-index)))

(defn is [media-format]
  "'true' if current screen is specified 'media-format': equal"
  (let [desired-index (:relative-size-index (media-format responsive-breakpoint))
        breakpoint @(subs/subscribe [:env:current-device-media-format])
        screen-index (:relative-size-index (breakpoint responsive-breakpoint))]
    (= screen-index desired-index)))

(defn up-to [media-format]
  "'true' if current screen is up-to specified 'media-format': smaller or equal"
  (let [desired-index (:relative-size-index (media-format responsive-breakpoint))
        breakpoint @(subs/subscribe [:env:current-device-media-format])
        screen-index (:relative-size-index (breakpoint responsive-breakpoint))]
    (<= screen-index desired-index)))


(defn up-from [media-format]
  "'true' if current screen is up-from specified 'media-format': equal or larger"
  (let [desired-index (:relative-size-index (media-format responsive-breakpoint))
        breakpoint @(subs/subscribe [:env:current-device-media-format])
        screen-index (:relative-size-index (breakpoint responsive-breakpoint))]
    (>= screen-index desired-index)))
