// swift-tools-version:5.10
import PackageDescription

let package = Package(
    name: "OrbitSDK",
    platforms: [
        .iOS(.v17.4)
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
