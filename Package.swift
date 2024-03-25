// swift-tools-version:5.3
import PackageDescription

let package = Package(
    name: "OrbitSDK",
    platforms: [
        .iOS(.v16)
    ],
    products: [
        .library(
            name: "OrbitSDK",
            targets: ["OrbitSDK"]
        ),
    ],
    targets: [
        .binaryTarget(
            name: "OrbitSDK",
            path: "./OrbitSDK.xcframework"
        ),
    ]
)
