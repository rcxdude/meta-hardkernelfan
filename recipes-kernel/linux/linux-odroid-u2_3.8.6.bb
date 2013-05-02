COMPATIBLE_MACHINE = "odroid-u2"

require linux.inc

DESCRIPTION = "Linux kernel for the Hardkernel ODROID-U2 board"

PR = "r0"

# Bump MACHINE_KERNEL_PR in the machine config if you update the kernel.
SRCREV = "e1aec5bf9f395156c8952c5654686c031d733c58"

SRC_URI = "git://github.com/hardkernel/linux.git;protocol=git;branch=odroid-3.8.y"

LINUX_VERSION ?= "3.8.6-odroid-u2"
PV = "${LINUX_VERSION}+${PR}+git${SRCREV}"

S = "${WORKDIR}/git"

# NOTE: For now we pull in the default Ubuntu config from the odroid kernel GIT tree.

KERNEL_DEFCONFIG = "odroidu2_defconfig"

# CMDLINE for odroid. No idea what to put here yet (been copied from the meta-rasberrypi layer)
CMDLINE_odroid-u2 = ""

PARALLEL_MAKEINST = ""

do_configure_prepend() {
	install -m 0644 ${S}/arch/${ARCH}/configs/${KERNEL_DEFCONFIG} ${WORKDIR}/defconfig || die "No default configuration for ${MACHINE} / ${KERNEL_DEFCONFIG} available."
}

do_install_prepend() {
	install -d ${D}/lib/firmware
}
